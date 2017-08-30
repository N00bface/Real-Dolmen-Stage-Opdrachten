/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

function processAQI(arr) {
    for (var i = 0; i < arr.length; i++) {
        var line = arr[i];
        var aqi = line[1];

        if (aqi <= 50) {
            line[7] = '"优"';
        }
        else if (aqi <= 100) {
            line[7] = '"良"';
        }
        else if (aqi <= 150) {
            line[7] = '"轻度污染"';
        }
        else if (aqi <= 200) {
            line[7] = '"中度污染"';
        }
        else if (aqi <= 300) {
            line[7] = '"重度污染"';
        }
        else {
            line[7] = '"严重污染"';
        }
    }

    console.log(arr.join('],\n    ['));
}
