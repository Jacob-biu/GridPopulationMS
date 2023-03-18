var map = new AMap.Map('mapContainer', {
  zoom: 10,
  center: [116.397428, 39.90923],
  resizeEnable: true
});


var geocoder = new AMap.Geocoder({
  city: "南京市"
});
geocoder.getLocation("南京市鼓楼区南京邮电大学", function(status, result) {
  if (status === 'complete' && result.info === 'OK') {
    var location = result.geocodes[0].location;
    map.setCenter(location);
    var marker = new AMap.Marker({
      position: location,
      map: map
    });
  } else {
    console.log("地理编码失败：" + result.info);
  }
});

AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.OverView'], function() {
  map.addControl(new AMap.ToolBar());
  map.addControl(new AMap.Scale());
  map.addControl(new AMap.OverView());
});
