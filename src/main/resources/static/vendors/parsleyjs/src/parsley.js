/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

import $ from 'jquery';
import Parsley from './parsley/main';
import './parsley/pubsub';
import './parsley/remote';
import './i18n/en';
import inputevent from './vendor/inputevent';

inputevent.install();

export default Parsley;
