/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.converter;

import org.dspace.app.rest.model.ClaimedTaskRest;
import org.dspace.app.rest.projection.Projection;
import org.dspace.discovery.IndexableObject;
import org.dspace.xmlworkflow.storedcomponents.ClaimedTask;
import org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is the converter from/to the laimTask in the DSpace API data model
 * and the REST data model
 *
 * @author Andrea Bollini (andrea.bollini at 4science.it)
 */
@Component
public class ClaimedTaskConverter
        implements IndexableObjectConverter<ClaimedTask, ClaimedTaskRest> {

    @Autowired
    private ConverterService converter;

    @Override
    public ClaimedTaskRest convert(ClaimedTask obj, Projection projection) {
        ClaimedTaskRest taskRest = new ClaimedTaskRest();
        taskRest.setProjection(projection);
        XmlWorkflowItem witem = obj.getWorkflowItem();
        taskRest.setId(obj.getID());
        taskRest.setWorkflowitem(converter.toRest(witem, projection));
        taskRest.setAction(obj.getActionID());
        taskRest.setStep(obj.getStepID());
        taskRest.setOwner(converter.toRest(obj.getOwner(), projection));
        return taskRest;
    }

    @Override
    public Class<ClaimedTask> getModelClass() {
        return ClaimedTask.class;
    }

    @Override
    public boolean supportsModel(IndexableObject object) {
        return object instanceof ClaimedTask;
    }

}
