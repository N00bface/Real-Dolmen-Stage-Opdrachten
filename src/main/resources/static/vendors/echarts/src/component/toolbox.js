/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    require('./toolbox/ToolboxModel');
    require('./toolbox/ToolboxView');

    require('./toolbox/feature/SaveAsImage');
    require('./toolbox/feature/MagicType');
    require('./toolbox/feature/DataView');
    require('./toolbox/feature/DataZoom');
    require('./toolbox/feature/Restore');
});
