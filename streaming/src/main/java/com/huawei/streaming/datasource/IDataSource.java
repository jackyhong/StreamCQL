/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huawei.streaming.datasource;

import java.io.Serializable;
import java.util.List;

import com.huawei.streaming.config.StreamingConfig;
import com.huawei.streaming.event.TupleEventType;
import com.huawei.streaming.exception.StreamingException;

/**
 * 数据源接口
 *
 */
public interface IDataSource extends Serializable
{

    /**
     * 设置配置属性
     * 编译时接口
     * 各种配置属性的缺失，可以在该阶段快速发现
     *
     * @param conf 配置属性
     * @throws StreamingException 参数设置异常，缺少参数或参数类型错误
     */
    void setConfig(StreamingConfig conf) throws StreamingException;

    /**
     * 设置用户的查询schema
     * @param eventType 查询结果对应的schema
     */
    void setSchema(TupleEventType eventType);

    /**
     * 运行时的初始化接口
     *
     * @throws StreamingException 流处理异常
     */
    void initialize() throws StreamingException;


    /**
     * 执行数据源查询操作
     * @param replacedQueryArguments 完成了参数替换的查询参数
     * @return 查询结果，按照查询schema的顺序列出的计算结果
     * @throws StreamingException 查询异常
     */
    List< Object[] > execute(List< Object > replacedQueryArguments) throws StreamingException;

    /**
     * 运行时的销毁接口
     *
     * @throws StreamingException 流处理异常
     */
    void destroy() throws StreamingException;
}
