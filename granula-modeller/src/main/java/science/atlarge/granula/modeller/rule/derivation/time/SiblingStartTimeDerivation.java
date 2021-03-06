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

package science.atlarge.granula.modeller.rule.derivation.time;

import science.atlarge.granula.modeller.platform.info.InfoSource;
import science.atlarge.granula.modeller.platform.operation.Operation;
import science.atlarge.granula.modeller.rule.derivation.DerivationRule;
import science.atlarge.granula.modeller.platform.info.BasicInfo;
import science.atlarge.granula.modeller.platform.info.Source;

import java.util.ArrayList;
import java.util.List;

public class SiblingStartTimeDerivation extends DerivationRule {

    String siblingActorType;
    String siblingMissionType;

    public SiblingStartTimeDerivation(int level, String siblingActorType, String siblingMissionType) {
        super(level);
        this.siblingActorType = siblingActorType;
        this.siblingMissionType = siblingMissionType;
    }

    @Override
    public boolean execute() {
        Operation operation = (Operation) entity;

        Operation lastSibling = null;

        Operation parent = operation.getParent();
        for (Operation sibling : parent.getChildren()) {
            if(sibling.hasType(siblingActorType, siblingMissionType)) {
                if(lastSibling == null) {
                    lastSibling = sibling;
                } else {
                    if(Long.parseLong(sibling.getInfo("EndTime").getValue()) >
                            Long.parseLong(lastSibling.getInfo("EndTime").getValue())) {
                        lastSibling = sibling;
                    }
                }
            }

        }

        long startTime = Long.parseLong(lastSibling.getInfo("EndTime").getValue());
        List<Source> sources = new ArrayList<>();
        sources.add(new InfoSource("EndTime", lastSibling.getInfo("EndTime")));

        BasicInfo info = new BasicInfo("StartTime");
        info.setDescription("The [StartTime] of this operation is derived from the [EndTime] of the last subling operation.");
        info.addInfo(String.valueOf(startTime), sources);
        operation.addInfo(info);
        return  true;
    }
}
