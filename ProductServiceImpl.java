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
import in.co.online.food.delivery.dao.ProductDAOInt;
import in.co.online.food.delivery.dto.ProductDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

@Service
public class ProductServiceImpl implements ProductServiceInt {

	private static Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	private ProductDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(ProductDTO dto) throws DuplicateRecordException {
		log.info("ProductServiceImpl Add method start");
		ProductDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null)
			throw new DuplicateRecordException("Name Already Exist");
		long pk = dao.add(dto);
		log.info("ProductServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ProductDTO dto) {
		log.info("ProductServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ProductServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public ProductDTO findBypk(long pk) {
		log.info("ProductServiceImpl findBypk method start");
		ProductDTO dto = dao.findBypk(pk);
		log.info("ProductServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ProductDTO findByName(String name) {
		log.info("ProductServiceImpl findByProductName method start");
		ProductDTO dto = dao.findByName(name);
		log.info("ProductServiceImpl findByProductName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ProductDTO dto) throws DuplicateRecordException {
		log.info("ProductServiceImpl update method start");
		ProductDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("ProductServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ProductDTO> list() {
		log.info("ProductServiceImpl list method start");
		List<ProductDTO> list = dao.list();
		log.info("ProductServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ProductDTO> list(int pageNo, int pageSize) {
		log.info("ProductServiceImpl list method start");
		List<ProductDTO> list = dao.list(pageNo, pageSize);
		log.info("ProductServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ProductDTO> search(ProductDTO dto) {
		log.info("ProductServiceImpl search method start");
		List<ProductDTO> list = dao.search(dto);
		log.info("ProductServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize) {
		log.info("ProductServiceImpl search method start");
		List<ProductDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ProductServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}


}
