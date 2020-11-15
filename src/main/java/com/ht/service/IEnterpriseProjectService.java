package com.ht.service;

import com.ht.model.EnterpriseProject;

import java.util.List;

public interface IEnterpriseProjectService {

    String createProject(EnterpriseProject enterpriseProject);

    String deleteProject(String projectId);

    String updateProject(EnterpriseProject enterpriseProject);

    // 按照项目名称模糊查询
    List<EnterpriseProject> getProjectList(String projectName);

    EnterpriseProject getProject(String projectId);

}
