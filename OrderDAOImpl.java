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

import in.co.online.food.delivery.dto.OrderDTO;

@Repository
public class OrderDAOImpl implements OrderDAOInt {

	private static Logger log = Logger.getLogger(OrderDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(OrderDTO dto) {
		log.info("OrderDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("OrderDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(OrderDTO dto) {
		log.info("OrderDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("OrderDAOImpl Delete method End");

	}

	@Override
	public OrderDTO findBypk(long pk) {
		log.info("OrderDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		OrderDTO dto = (OrderDTO) session.get(OrderDTO.class, pk);
		log.info("OrderDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public OrderDTO findByName(String name) {
		log.info("OrderDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrderDTO.class);
		criteria.add(Restrictions.eq("name", name));
		OrderDTO dto = (OrderDTO) criteria.uniqueResult();
		log.info("OrderDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(OrderDTO dto) {
		log.info("OrderDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("OrderDAOImpl update method End");
	}

	@Override
	public List<OrderDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<OrderDTO> list(int pageNo, int pageSize) {
		log.info("OrderDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<OrderDTO> query = session.createQuery("from OrderDTO", OrderDTO.class);
		List<OrderDTO> list = query.getResultList();
		log.info("OrderDAOImpl List method End");
		return list;
	}

	@Override
	public List<OrderDTO> search(OrderDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<OrderDTO> search(OrderDTO dto, int pageNo, int pageSize) {
		log.info("OrderDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from OrderDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getOrderid() > 0) {
				hql.append("and u.orderid = " + dto.getOrderid());
			}
			
			if (dto.getUserId() > 0) {
				hql.append("and u.userId = " + dto.getUserId());
			}
			

		}
		Query<OrderDTO> query = session.createQuery(hql.toString(), OrderDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<OrderDTO> list = query.getResultList();
		log.info("OrderDAOImpl Search method End");
		return list;
	}

	

}
