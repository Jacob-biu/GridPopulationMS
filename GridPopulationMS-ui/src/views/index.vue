<template>
  <div id="map-container"></div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';

export default {
  data() {
    return {
      map: null,
      searchValue: "",
      searchResults: []
    };
  },
  mounted() {
    //DOM初始化完成进行地图初始化
    this.initMap();

  },

  methods:{
    initMap(){
      window._AMapSecurityConfig = {
        securityJsCode:'18f8ca190c9bf437f7a6c4af79719d62',
      }
      AMapLoader.load({
        key:"522861cd1297e6da70e3562625801e71",             // 申请好的Web端开发者Key，首次调用 load 时必填
        version:"",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins:[''],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
        "AMapUI": {             // 是否加载 AMapUI，缺省不加载
          "version": '1.1',   // AMapUI 版本
          "plugins": ['overlay/SimpleMarker'],       // 需要加载的 AMapUI ui插件
        },
        "Loca":{                // 是否加载 Loca， 缺省不加载
          "version": ''  // Loca 版本
        },
      }).then((AMap)=>{
        const buildings = new AMap.Buildings({
          'zooms': [16, 18],
          'heightFactor': 2//2倍于默认高度，3D下有效
        });//楼块图层

        this.map = new AMap.Map("map-container",{  //设置地图容器id
          viewMode:"3D",    //是否为3D地图模式
          zoom:15,           //初始化地图级别
          zooms: [2, 22],
          center:[118.7703,32.0816], //初始化地图中心点位置,
          layers: [new AMap.TileLayer(),//高德默认标准图层
            buildings],
        });

      }).catch(e=>{
        console.log(e);
      })
    },


  },

};
</script>

<style>
#map-container{
  position:absolute;
  width: 100%;
  height: 100%;
  z-index: 2;
}
#search_bar{
  width: 25%;
  position: relative;
  z-index: 1;
  background-color: #e7eaec00;
}
</style>
