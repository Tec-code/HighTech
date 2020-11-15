package com.ht.dao.impl;

import com.ht.dao.IEnterpriseProjectDao;
import com.ht.dao.mapper.EnterpriseProjectMapper;
import com.ht.model.EnterpriseProject;

import java.util.List;

public class EnterpriseProjectDaoImpl extends BaseDao implements IEnterpriseProjectDao {
    @Override
    public int insertEnterpriseProject(EnterpriseProject enterpriseProject) {
        EnterpriseProjectMapper mapper = sqlSession.getMapper(EnterpriseProjectMapper.class);
        return mapper.insert(enterpriseProject);
    }

    @Override
    public String updateEnterpriseProject(EnterpriseProject enterpriseProject) {
        EnterpriseProjectMapper mapper = sqlSession.getMapper(EnterpriseProjectMapper.class);
        mapper.updateByPrimaryKeySelective(enterpriseProject);
        return enterpriseProject.getProjectId();
    }

    @Override
    public List<EnterpriseProject> getEnterpriseProjectByProjectName(String projectName) {
        EnterpriseProjectMapper mapper = sqlSession.getMapper(EnterpriseProjectMapper.class);
        return mapper.selectByProjectName(projectName);
    }

    @Override
    public String deleteEnterpriseProject(String projectId) {
        EnterpriseProjectMapper mapper = sqlSession.getMapper(EnterpriseProjectMapper.class);
        mapper.deleteByPrimaryKey(projectId);
        return projectId;
    }

    @Override
    public EnterpriseProject getEnterpriseProjectByProjectId(String projectId) {
        EnterpriseProjectMapper mapper = sqlSession.getMapper(EnterpriseProjectMapper.class);
        return mapper.selectByPrimaryKey(projectId);
    }
}
