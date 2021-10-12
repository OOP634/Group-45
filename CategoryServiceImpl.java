package in.co.online.food.delivery.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.co.online.food.delivery.dao.CategoryDAOInt;
import in.co.online.food.delivery.dto.CategoryDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

@Service
public class CategoryServiceImpl implements CategoryServiceInt {

	private static Logger log = Logger.getLogger(CategoryServiceImpl.class.getName());

	@Autowired
	private CategoryDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(CategoryDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl Add method start");
		CategoryDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null)
			throw new DuplicateRecordException("Name Already Exist");
		long pk = dao.add(dto);
		log.info("CategoryServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(CategoryDTO dto) {
		log.info("CategoryServiceImpl Delete method start");
		dao.delete(dto);
		log.info("CategoryServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public CategoryDTO findBypk(long pk) {
		log.info("CategoryServiceImpl findBypk method start");
		CategoryDTO dto = dao.findBypk(pk);
		log.info("CategoryServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public CategoryDTO findByName(String name) {
		log.info("CategoryServiceImpl findByCategoryName method start");
		CategoryDTO dto = dao.findByName(name);
		log.info("CategoryServiceImpl findByCategoryName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(CategoryDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl update method start");
		CategoryDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("CategoryServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<CategoryDTO> list() {
		log.info("CategoryServiceImpl list method start");
		List<CategoryDTO> list = dao.list();
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<CategoryDTO> list(int pageNo, int pageSize) {
		log.info("CategoryServiceImpl list method start");
		List<CategoryDTO> list = dao.list(pageNo, pageSize);
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<CategoryDTO> search(CategoryDTO dto) {
		log.info("CategoryServiceImpl search method start");
		List<CategoryDTO> list = dao.search(dto);
		log.info("CategoryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<CategoryDTO> search(CategoryDTO dto, int pageNo, int pageSize) {
		log.info("CategoryServiceImpl search method start");
		List<CategoryDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CategoryServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}


}
