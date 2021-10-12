package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.online.food.delivery.dto.CategoryDTO;

@Repository
public class CategoryDAOImpl implements CategoryDAOInt {

	private static Logger log = Logger.getLogger(CategoryDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(CategoryDTO dto) {
		log.info("CategoryDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CategoryDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(CategoryDTO dto) {
		log.info("CategoryDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("CategoryDAOImpl Delete method End");

	}

	@Override
	public CategoryDTO findBypk(long pk) {
		log.info("CategoryDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		CategoryDTO dto = (CategoryDTO) session.get(CategoryDTO.class, pk);
		log.info("CategoryDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public CategoryDTO findByName(String name) {
		log.info("CategoryDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CategoryDTO.class);
		criteria.add(Restrictions.eq("name", name));
		CategoryDTO dto = (CategoryDTO) criteria.uniqueResult();
		log.info("CategoryDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(CategoryDTO dto) {
		log.info("CategoryDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CategoryDAOImpl update method End");
	}

	@Override
	public List<CategoryDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<CategoryDTO> list(int pageNo, int pageSize) {
		log.info("CategoryDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<CategoryDTO> query = session.createQuery("from CategoryDTO", CategoryDTO.class);
		List<CategoryDTO> list = query.getResultList();
		log.info("CategoryDAOImpl List method End");
		return list;
	}

	@Override
	public List<CategoryDTO> search(CategoryDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<CategoryDTO> search(CategoryDTO dto, int pageNo, int pageSize) {
		log.info("CategoryDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from CategoryDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}

		}
		Query<CategoryDTO> query = session.createQuery(hql.toString(), CategoryDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<CategoryDTO> list = query.getResultList();
		log.info("CategoryDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		CategoryDTO person = (CategoryDTO) session.get(CategoryDTO.class, id);
		byte[] blob = person.getImages();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
