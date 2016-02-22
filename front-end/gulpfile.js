var gulp = require('gulp');
var gutil = require('gulp-util')
var browserSync = require('browser-sync').create();
var del = require('del');
var less = require('gulp-less');
var webpack = require('webpack');
var webpackStream = require('webpack-stream');
var named = require('vinyl-named');
var runSequence = require('run-sequence');
var BrowserSyncReloaderPlugin = require('browsersync-reloader-webpack-plugin');
var minifyCSS = require('gulp-minify-css');

var DIST_PATH = "./dist";
var mode = "build";

var commonChunkPlugin = new webpack.optimize.CommonsChunkPlugin('vendors', 'vendors.js');

var wepackOptions = {
    build: {
        watch: true,
        plugins: [
            commonChunkPlugin,
            new BrowserSyncReloaderPlugin({browserSync: browserSync})
        ]
    },
    dist: {
        plugins: [
            commonChunkPlugin,
            new webpack.optimize.UglifyJsPlugin({
                minimize: true
            })
        ]
    }
}

gulp.task('scripts', function () {
    gulp.src([
            'src/vendors.js',
            'src/index.js'
        ])
        .pipe(named())
        .pipe(webpackStream(wepackOptions[mode]))
        .pipe(gulp.dest(DIST_PATH + '/js'));
});


gulp.task('styles',function() {
  gulp.src('less/app.less')
    .pipe(less())
    .pipe(mode === "dist" ? minifyCSS() : gutil.noop())
    .pipe(gulp.dest(DIST_PATH + '/css/'))
    .pipe(mode === "dist" ? gutil.noop() : browserSync.stream({once: true}));

});

gulp.task('lib',function() {
  gulp.src([
        'node_modules/bootflat/bootflat/css/**',
        'node_modules/font-awesome/css/**'
    ])
    .pipe(gulp.dest(DIST_PATH + '/css/'))

  gulp.src('node_modules/bootflat/bootflat/img/**')
    .pipe(gulp.dest(DIST_PATH + '/img/'))

    gulp.src(['node_modules/bootstrap/dist/fonts/**', 'node_modules/font-awesome/fonts/**'])
    .pipe(gulp.dest(DIST_PATH + '/fonts/'))

});

gulp.task('browser-sync', function() {
    browserSync.init({
        reload: true,
        host: 'localhost',
        port: 3000,
        proxy: "localhost:8080",
    });
});

gulp.task('clean', function() {
    return del([DIST_PATH]);
});

var build = ['scripts', 'styles', 'lib'];

gulp.task('build', function(cb) {
    wepackOptions.build.watch = false;

    runSequence('clean', build, cb);
});

gulp.task('serve', function(cb) {
    gulp.watch('less/**/*', ['styles']);

    runSequence('clean', build, 'browser-sync', cb);
});

gulp.task('dist' , function(cb) {
    mode = "dist";

    runSequence(build, cb);
});

gulp.task('default', ['serve']);