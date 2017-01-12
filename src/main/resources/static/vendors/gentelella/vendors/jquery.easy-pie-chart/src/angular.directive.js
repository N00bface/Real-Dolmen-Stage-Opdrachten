/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

(function (angular) {

	'use strict';

	return angular.module('easypiechart', [])

		.directive('easypiechart', [function() {
			return {
				restrict: 'A',
				require: '?ngModel',
				scope: {
					percent: '=',
					options: '='
				},
				link: function (scope, element, attrs) {

					scope.percent = scope.percent || 0;

					/**
					 * default easy pie chart options
					 * @type {Object}
					 */
					var options = {
						barColor: '#ef1e25',
						trackColor: '#f9f9f9',
						scaleColor: '#dfe0e0',
						scaleLength: 5,
						lineCap: 'round',
						lineWidth: 3,
						size: 110,
						rotate: 0,
						animate: {
							duration: 1000,
							enabled: true
						}
					};
					scope.options = angular.extend(options, scope.options);

					var pieChart = new EasyPieChart(element[0], options);

					scope.$watch('percent', function(newVal, oldVal) {
						pieChart.update(newVal);
					});
				}
			};
		}]);

})(angular);