

//require('@weex-module/xxx') : 依赖一个 Weex 原生模块

var stream
__weex_define__('@weex-temp/api', function (__weex_require__) {
    stream = __weex_require__('@weex-module/stream')
});

var apiURL = {
    baseurl: 'http://testapp.likingfit.com/v2',
    home_banner: "/index/banner",
};

function getData(url, callback) {
    stream.sendHttp({
        method: 'GET',
        url: url
    }, function (ret) {
        var retdata = JSON.parse(ret);
        callback(retdata);
    });
}

function getPostData(url , parms, callback) {
    stream.fetch({
        method: 'POST',
        url: url,
        type:'text',
        body:parms //or you can just use JSON Object {username:'weex'}
    }, function(ret) {
        if(!ret.ok){
            // me.postResult = "request failed";
            console.log('request failed');
        }else{

            // var retdata = JSON.parse(ret);
            //
            console.log('get:'+JSON.stringify(ret));
            nativeLog('request:-----' + JSON.stringify(ret));
            callback(ret);
            // me.postResult = ;
        }
    },function(response){
        console.log('get in progress:'+response.length);
        // me.postResult = "bytes received:"+response.length;
    });
}

exports.getHomeBanner = function (parms, callback) {
    getPostData(apiURL.baseurl + apiURL.home_banner, parms, callback);
};