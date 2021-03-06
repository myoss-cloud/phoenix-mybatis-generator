/*
 * Copyright 2018-2018 https://github.com/myoss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package app.myoss.cloud.mybatis.generator;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zaxxer.hikari.HikariDataSource;

import app.myoss.cloud.core.exception.BizRuntimeException;
import app.myoss.cloud.mybatis.generator.config.Configuration;
import app.myoss.cloud.mybatis.generator.config.PropertyRegistry;
import app.myoss.cloud.mybatis.generator.config.TableConfiguration;
import app.myoss.cloud.mybatis.table.annotation.GenerationType;
import app.myoss.cloud.mybatis.table.annotation.SequenceGenerator.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用h2内存数据库进行集成单元测试
 *
 * @author Jerry.Chen
 * @since 2018年5月10日 下午11:19:19
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DataSourceAutoConfiguration.class })
public class H2DataBaseIntCase1Tests {
    @Autowired
    private HikariDataSource dataSource;

    public static String getRootOutputPath(String methodName, boolean saveFileToMyossMybatisTestSrc) {
        Objects.requireNonNull(methodName);
        Path targetFolder;
        try {
            targetFolder = Paths.get(H2DataBaseIntCase1Tests.class.getResource("/").toURI()).getParent();
        } catch (URISyntaxException ex) {
            throw new BizRuntimeException(ex);
        }
        Objects.requireNonNull(targetFolder);
        if (saveFileToMyossMybatisTestSrc) {
            return targetFolder.getParent().getParent().resolve("myoss-mybatis/src/test").toString();
        } else {
            return targetFolder.resolve("generated-mybatis" + File.separator + methodName).toString();
        }
    }

    /**
     * 测试生成普通 Crud 文件。参考
     * {@link app.myoss.cloud.mybatis.test.integration.h2.test1.UserControllerIntTests}
     * 集成测试类
     */
    @Test
    public void generateTest1() {
        String rootOutputPath = getRootOutputPath("generateTest1", false);

        List<TableConfiguration> tableConfigurations = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTableConfigurations(tableConfigurations);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setRemoveEntityClassPrefix("TSys");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test1");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月11日 上午10:41:47");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, true);

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user");
        tableConfigurations.add(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }

    /**
     * 测试生成普通 Crud
     * 文件。使用：templates/freemarker/webRestApi.java.ftl，{@link PropertyRegistry#ALL_REST_CONTROLLER_ENABLE_IN_WEB_FILE}
     * 的值为：false
     */
    @Test
    public void generateTest1_1() {
        String rootOutputPath = getRootOutputPath("generateTest1_1", false);

        List<TableConfiguration> tableConfigurations = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTableConfigurations(tableConfigurations);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setRemoveEntityClassPrefix("TSys");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test1");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月11日 上午10:41:47");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, true);
        configuration.addProperty(PropertyRegistry.ALL_REST_CONTROLLER_ENABLE_IN_WEB_FILE, false);
        configuration.setWebTemplatePath("templates/freemarker/webRestApi2.java.ftl");

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user");
        tableConfigurations.add(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }

    /**
     * 测试生成普通 Crud
     * 文件。使用：templates/freemarker/webRestApi2.java.ftl，{@link PropertyRegistry#ALL_REST_CONTROLLER_ENABLE_IN_WEB_FILE}
     * 的值为：true
     */
    @Test
    public void generateTest1_2() {
        String rootOutputPath = getRootOutputPath("generateTest1_2", false);

        List<TableConfiguration> tableConfigurations = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTableConfigurations(tableConfigurations);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setRemoveEntityClassPrefix("TSys");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test1");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月11日 上午10:41:47");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, true);
        configuration.addProperty(PropertyRegistry.ALL_REST_CONTROLLER_ENABLE_IN_WEB_FILE, true);
        configuration.setWebTemplatePath("templates/freemarker/webRestApi2.java.ftl");

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user");
        tableConfigurations.add(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }

    /**
     * 测试生成 {@link SelectKey} Crud 文件。参考
     * {@link app.myoss.cloud.mybatis.test.integration.h2.test2.UserHistoryControllerIntTests}
     * 集成测试类
     */
    @Test
    public void generateTest2() {
        String rootOutputPath = getRootOutputPath("generateTest2", false);

        List<TableConfiguration> tableConfigurations = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTableConfigurations(tableConfigurations);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setEntitySuperClass("app.myoss.cloud.mybatis.repository.entity.AuditIdEntity");
        configuration.setRemoveEntityClassPrefix("TSys");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test2");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月14日 下午3:39:43");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, true);
        configuration.addProperty(PropertyRegistry.SELECT_KEY_SELECT_SQL,
                "select ifnull(max(`id`) ,0) + 1 from t_sys_user_history");
        configuration.addProperty(PropertyRegistry.SELECT_KEY_RESULT_TYPE, "Long");
        configuration.addProperty(PropertyRegistry.SELECT_KEY_ORDER,
                Order.class.getSimpleName() + "." + Order.BEFORE.name());
        configuration.addProperty(PropertyRegistry.USE_PRIMARY_KEY_JAVA_TYPE_FOR_CLASS_GENERIC_TYPE_IN_ENTITY_FILE,
                true);
        configuration.setSequenceStrategy(GenerationType.SELECT_KEY);

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user_history");
        tableConfigurations.add(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }

    /**
     * 测试生成 {@link SequenceKey} Crud 文件。参考
     * {@link app.myoss.cloud.mybatis.test.integration.h2.test3.UserLogControllerIntTests}
     * 集成测试类
     */
    @Test
    public void generateTest3() {
        String rootOutputPath = getRootOutputPath("generateTest3", false);

        List<TableConfiguration> tableConfigurations = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTableConfigurations(tableConfigurations);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setEntitySuperClass("app.myoss.cloud.mybatis.repository.entity.AuditIdEntity");
        configuration.setRemoveEntityClassPrefix("TSys");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test3");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月14日 下午10:33:55");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, false);
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_CLASS, "SequenceCustomizer");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_NAME, "sequenceUserLog");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_BEAN_NAME, "seqUserLog");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_ORDER,
                Order.class.getSimpleName() + "." + Order.BEFORE.name());
        configuration.addProperty(PropertyRegistry.USE_PRIMARY_KEY_JAVA_TYPE_FOR_CLASS_GENERIC_TYPE_IN_ENTITY_FILE,
                true);
        configuration.setSequenceStrategy(GenerationType.SEQUENCE_KEY);
        configuration.addEntityImportPackage(
                "app.myoss.cloud.mybatis.test.integration.h2.H2DataBaseIntTest.SequenceCustomizer");
        //        configuration.setSequenceTemplatePath("*/templates/freemarker/copyright.ftl");

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user_log");
        tableConfigurations.add(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }

    /**
     * 测试生成 {@link SequenceKey} Crud 文件。参考
     * {@link app.myoss.cloud.mybatis.test.integration.h2.test4.SysUserLogControllerIntTests}
     * 集成测试类
     */
    @Test
    public void generateTest4() {
        String rootOutputPath = getRootOutputPath("generateTest4", false);

        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setAuthor("jerry");
        configuration.setCopyright("Copyright 2018-${todayYear} https://github.com/myoss\n" + "\n"
                + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
                + "you may not use this file except in compliance with the License.\n"
                + "You may obtain a copy of the License at\n" + "\n"
                + "    http://www.apache.org/licenses/LICENSE-2.0\n" + "\n"
                + "Unless required by applicable law or agreed to in writing, software\n"
                + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
                + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                + "See the License for the specific language governing permissions and\n"
                + "limitations under the License.\n");
        configuration.setEntitySuperClass("app.myoss.cloud.mybatis.repository.entity.AuditIdEntity");
        configuration.setRemoveEntityClassPrefix("T");
        configuration.setRootPackageName("app.myoss.cloud.mybatis.test.integration.h2.test4");
        configuration.setRootOutputPath(rootOutputPath);
        configuration.setGenerateDate("2018年5月14日 下午10:33:55");
        configuration.addProperty(PropertyRegistry.ALL_METHOD_ENABLE_IN_WEB_FILE, false);
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_CLASS, "Sequence");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_CLASS_NAME,
                "app.myoss.cloud.mybatis.test.integration.h2.test4.SysUserLogControllerIntTests.SequenceCustomizer");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_SEQUENCE_NAME, "sequenceUserLog");
        configuration.addProperty(PropertyRegistry.SEQUENCE_KEY_ORDER,
                Order.class.getSimpleName() + "." + Order.BEFORE.name());
        configuration.addProperty(PropertyRegistry.USE_PRIMARY_KEY_JAVA_TYPE_FOR_CLASS_GENERIC_TYPE_IN_ENTITY_FILE,
                true);
        configuration.setSequenceStrategy(GenerationType.SEQUENCE_KEY);
        configuration.addEntityImportPackage("app.myoss.cloud.mybatis.table.Sequence");
        //        configuration.setSequenceTemplatePath("*/templates/freemarker/copyright.ftl");

        TableConfiguration tableConfiguration = new TableConfiguration();
        tableConfiguration.setTableName("t_sys_user_log");
        configuration.addTableConfiguration(tableConfiguration);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration);
        myBatisGenerator.generate();
    }
}
