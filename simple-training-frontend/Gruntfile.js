module.exports = function (grunt) {
  grunt.loadNpmTasks('grunt-http-server');
  // Project configuration.
  grunt.initConfig({
    'http-server': {
	'dev': {
	  root: 'www',
	  openBrowser : true,
	  port: 8282,
	  host: "localhost",
	  runInBackground: false,
	}
  }});
};
