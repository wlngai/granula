/*
 * Copyright 2015 - 2017 Atlarge Research Team,
 * operating at Technische Universiteit Delft
 * and Vrije Universiteit Amsterdam, the Netherlands.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package science.atlarge.granula.monitor.comm.task;

/**
 * Created by wlngai on 9-5-16.
 */
public enum TaskType {

    Monitor("MonitorContext", MonitorContext.class),
    Collect("CollectContext", CollectContext.class);

    public String id;
    public Class clazz;

    TaskType(String id, Class clazz){
        this.id = id;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public Class getClazz() {
        return clazz;
    }
}
