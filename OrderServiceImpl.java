package in.co.online.food.delivery.service;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.co.online.food.delivery.dao.OrderDAOInt;
import in.co.online.food.delivery.dto.OrderDTO;
import in.co.online.food.delivery.exception.DuplicateRecordException;

@Service
public class OrderServiceImpl implements OrderServiceInt {

	private static Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

	@Autowired
	private OrderDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(OrderDTO dto) throws DuplicateRecordException {
		log.info("OrderServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("OrderServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(OrderDTO dto) {
		log.info("OrderServiceImpl Delete method start");
		dao.delete(dto);
		log.info("OrderServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public OrderDTO findBypk(long pk) {
		log.info("OrderServiceImpl findBypk method start");
		OrderDTO dto = dao.findBypk(pk);
		log.info("OrderServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public OrderDTO findByName(String name) {
		log.info("OrderServiceImpl findByOrderName method start");
		OrderDTO dto = dao.findByName(name);
		log.info("OrderServiceImpl findByOrderName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(OrderDTO dto) throws DuplicateRecordException {
		log.info("OrderServiceImpl update method start");
		dao.update(dto);
		log.info("OrderServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<OrderDTO> list() {
		log.info("OrderServiceImpl list method start");
		List<OrderDTO> list = dao.list();
		log.info("OrderServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<OrderDTO> list(int pageNo, int pageSize) {
		log.info("OrderServiceImpl list method start");
		List<OrderDTO> list = dao.list(pageNo, pageSize);
		log.info("OrderServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<OrderDTO> search(OrderDTO dto) {
		log.info("OrderServiceImpl search method start");
		List<OrderDTO> list = dao.search(dto);
		log.info("OrderServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<OrderDTO> search(OrderDTO dto, int pageNo, int pageSize) {
		log.info("OrderServiceImpl search method start");
		List<OrderDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("OrderServiceImpl search method end");
		return list;
	}
	

}
