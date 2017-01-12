/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module.exports = function (grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    jshint: {
      all: ['gruntfile.js', 'gulpfile.js', 'src/**/*.js']
    },
    bootlint: {
      options: {},
      files: ['*.html', 'examples/**/*.html']
    },
    checkPages: {
      development: {
        options: {
          pageUrls: [
            'index.html',
            'examples/basic.html',
            'examples/clear-formatting.html',
            'examples/events.html',
            'examples/form-post.html',
            'examples/formatblock-example.html',
            'examples/html-editor.html',
            'examples/multiple-editors.html',
            'examples/simple-toolbar.html'
          ],
          checkLinks: true,
          summary: true
        }
      }
    },
    uglify: {
      options: {
        banner: '/* @fileoverview \n' + 
                ' * Provides full Bootstrap based, multi-instance WYSIWYG editor. \n' + 
                ' * \n' + 
                ' * Name     = ' + '<%= pkg.name %> \n' + 
                ' * Author   = ' + 'Various, see LICENCE \n' + 
                ' * Version  = ' + 'v<%= pkg.version %> \n' +
                ' * About    = ' + 'A tiny Bootstrap and jQuery based WYSIWYG rich text editor based on the browser function execCommand. \n' + 
                '*/ \n\n'
      },
      dist: {
        files: {
          'js/bootstrap-wysiwyg.min.js': ['src/**/*.js']
        },
      }
    },
    release: {
      options: {
        additionalFiles: ['bower.json', 'src/bootstrap-wysiwyg.js'],
        commit: false,
        npm: false,
        npmTag: false,
        push: false,
        pushTags: false,
        tag: false
      } 
    },
    watch: {
      files: ['gruntfile.js', 'gulpfile.js', 'src/**/*.js', '*.html', 'examples/**/*.html'],
      tasks: ['jshint', 'bootlint', 'checkPages', 'uglify']
    }
  });

  grunt.loadNpmTasks('grunt-check-pages');
  grunt.loadNpmTasks('grunt-bootlint');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-rename');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-release');

  grunt.registerTask('default', ['jshint', 'bootlint', 'checkPages', 'uglify', 'watch']);
};