package net.hs.easyj.crud.api.impl;


import net.hs.easyj.crud.api.GeneralUpdateAPI;
import net.hs.easyj.crud.dao.GeneralUpdateDao;
import net.hs.easyj.crud.dao.UpdateInDefinitionDao;
import net.hs.easyj.crud.model.UpdateDefinition;
import net.hs.easyj.crud.dao.UpdateDefinitionDao;
import net.hs.easyj.crud.model.UpdateInDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


/**
 * 通用更新 API 实现
 *
 * @author Gavin Hu
 * @create 2015/4/27
 */
@Service
public class GeneralUpdateAPIImpl implements GeneralUpdateAPI {

	@Autowired
	private UpdateDefinitionDao updateDefinitionDao;

	@Autowired
	private UpdateInDefinitionDao updateInDefinitionDao;

	@Autowired
	private GeneralUpdateDao generalUpdateDao;

	private T2ServiceInvoker t2ServiceInvoker = new T2ServiceInvoker();

	public UpdateDefinition load(String name) {
		UpdateDefinition example = new UpdateDefinition();
		example.setName(name);
		example.setEnabled(Boolean.TRUE);
		UpdateDefinition updateDefinition = updateDefinitionDao.findByExample(
				example).get(0);
		//
		UpdateInDefinition updateInDefinition = new UpdateInDefinition();
		updateInDefinition.setUpdateId(updateDefinition.getId());
		List<UpdateInDefinition> updateInDefinitions = updateInDefinitionDao
				.findByExample(updateInDefinition);

		updateDefinition.setInDefinitions(updateInDefinitions);

		return updateDefinition;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void update(Map params, UpdateDefinition definition) {
		Assert.notNull(definition, "Param definition cannot be null.");
		Assert.notNull(definition.getImplType(),
				"Param definition.getImplType() cannot be null.");
		Assert.notNull(definition.getImplType().length() == 3,
				"Param definition.getImplType() should have 3 chars.");
		switch (definition.getImplType().charAt(0)) {
		case Constants.COMMON_OPERATION_IMPL_TYPE_SQL:
			generalUpdateDao.update(params, definition);
			break;
		case Constants.COMMON_OPERATION_IMPL_TYPE_SERVICEWITHT2:
			String serviceIdentity = definition.getUpdate1();
			t2ServiceInvoker.invoke(serviceIdentity, params);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insert(Map params, UpdateDefinition definition) {
		Assert.notNull(definition, "Param definition cannot be null.");
		Assert.notNull(definition.getImplType(),
				"Param definition.getImplType() cannot be null.");
		Assert.notNull(definition.getImplType().length() == 3,
				"Param definition.getImplType() should have 3 chars.");
		switch (definition.getImplType().charAt(1)) {
		case Constants.COMMON_OPERATION_IMPL_TYPE_SQL:
			generalUpdateDao.insert(params, definition);
			break;
		case Constants.COMMON_OPERATION_IMPL_TYPE_SERVICEWITHT2:
			String serviceIdentity = definition.getUpdate2();
			t2ServiceInvoker.invoke(serviceIdentity, params);
			break;
		default:
			break;
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void delete(Map params, UpdateDefinition definition) {
		Assert.notNull(definition, "Param definition cannot be null.");
		Assert.notNull(definition.getImplType(),
				"Param definition.getImplType() cannot be null.");
		Assert.notNull(definition.getImplType().length() == 3,
				"Param definition.getImplType() should have 3 chars.");
		switch (definition.getImplType().charAt(2)) {
		case Constants.COMMON_OPERATION_IMPL_TYPE_SQL:
			generalUpdateDao.delete(params, definition);
			break;
		case Constants.COMMON_OPERATION_IMPL_TYPE_SERVICEWITHT2:
			String serviceIdentity = definition.getUpdate3();
			t2ServiceInvoker.invoke(serviceIdentity, params);
			break;
		default:
			break;
		}		
	}

}
