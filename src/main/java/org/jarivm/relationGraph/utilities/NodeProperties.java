package org.jarivm.relationGraph.utilities;

import org.jarivm.relationGraph.objects.domains.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jari Van Melckebeke
 * @since 24.09.16
 */
public class NodeProperties {

    public static List<Long> getRelation(Employee a, Employee b) {
        //todo: reconstruct path
        List<Long> nodes = new ArrayList<Long>();
        nodes.add(a.getId());

        return nodes;
    }
}
