<template>
  <div>
    <div id="map-container">
      <div class="search-bar" id="search_bar">
        <el-input v-model="searchKeyword" placeholder="请输入地址"></el-input>
        <el-button @click="search" >搜索</el-button>
      </div>
      <div id="panel" v-show="showSearchPanel">
        <div id="result">
          <ul>
            <li v-for="(item, index) in searchResult" :key="index">
              <a href="#" @click="goTo(item)">{{ item.name }}</a>
            </li>
          </ul>
        </div>
        <div id="operation">
          <el-button @click="nextPage">下一页</el-button>
          <el-button @click="toggleSearchPanel">关闭</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';

export default {
  data() {
    return {
      map: null,
      markers:[],
      infoWindow: null, // 信息窗口实例
      showSearchPanel: false,
      searchKeyword: '',
      searchResult: [],
      currentPage: 0,
      pageSize: 8,
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
        plugins:['AMap.Geolocation', 'AMap.PlaceSearch','AMap.Geocoder','AMap.Marker'],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
        "AMapUI": {             // 是否加载 AMapUI，缺省不加载
          "version": '1.1',   // AMapUI 版本
          "plugins": ['overlay/SimpleMarker'],       // 需要加载的 AMapUI ui插件
        },
        "Loca":{                // 是否加载 Loca， 缺省不加载
          "version": ''  // Loca 版本
        },
      }).then((AMap)=>{

        this.map = new AMap.Map("map-container",{  //设置地图容器id
          viewMode:"3D",    //是否为3D地图模式
          zoom:15,           //初始化地图级别
          // zooms: [2, 22],
          center:[118.7703,32.0816], //初始化地图中心点位置,
        });

        // 创建信息窗体
        const infoWindow = new AMap.InfoWindow({
          isCustom: true,
          offset: new AMap.Pixel(0, -20) // 窗体位置偏移量
        });

        // 添加地图点击事件监听器
        this.map.on("click", (event) => {
          const { lnglat } = event;
          // 移除之前的标记点
          this.map.remove(this.markers);

          // 添加新的标记点
          const marker = new AMap.Marker({
            position: lnglat,
          });

          // this.map.add(marker);
          this.markers.push(marker);
          // 将地图中心点移动到被点击的地点
          this.map.setCenter(lnglat);
          this.map.add(marker);

          // 使用地理编码插件获取地点信息
          const geocoder = new AMap.Geocoder({
            city: '南京市' // 默认城市
          });
          geocoder.getAddress(lnglat, (status, result) => {
            if (status === 'complete' && result.regeocode) {
              // 在标记上方显示地点信息
              const address = result.regeocode.formattedAddress;
              infoWindow.setContent(`<div style="text-align: center; background-color: hsla(0, 0%, 100%, 0.85);">${address}</div>`);
              infoWindow.open(this.map, lnglat);
            } else {
              // 获取地点信息失败
              console.log('获取地点信息失败');
            }
          });

        });



      }).catch(e=>{
        console.log(e);
      })
    },

    search() {
      this.showSearchPanel = true;
      const keywords = this.searchKeyword;
      const map = this.map;
      if (!map) {
        return;
      }

      AMap.plugin(["AMap.PlaceSearch"], () => {
        const placeSearch = new AMap.PlaceSearch({
          pageSize: this.pageSize,
          pageIndex: this.currentPage,
          city: "",
          map: map,
          panel: "result",
        });

        placeSearch.search(keywords, (status, result) => {
          if (status === "complete" && result.info === "OK") {
            const poiList = result.poiList;
            if (poiList && poiList.length > 0) {
              this.searchResult = poiList;
              const poi = poiList[0];
              const location = poi.location;
              const marker = new AMap.Marker({
                position: location,
                title: poi.name,
              });
              map.setCenter(location);
              map.clearMap();
              marker.setMap(map);
            }
          }
        });
      });
    },

    toggleSearchPanel() {
      this.showSearchPanel = !this.showSearchPanel;
    },

    nextPage() {
      this.currentPage++;
      const map = this.map;
      if (!map) {
        return;
      }

      AMap.plugin(["AMap.PlaceSearch"], () => {
        const placeSearch = new AMap.PlaceSearch({
          pageSize: this.pageSize,
          pageIndex: this.currentPage,
          city: "",
          map: map,
          panel: "result",
        });

        placeSearch.search(this.searchText, (status, result) => {
          if (status === "complete" && result.info === "OK") {
            const poiList = result.poiList;
            if (poiList && poiList.length > 0) {
              this.searchResult = poiList;
            }
          }
        });
      });
    },

    goTo(item) {
      const location = item.location;
      const marker = new AMap.Marker({
        position: location,
        title: item.name,
      });
      this.map.setCenter(location);
      this.map.clearMap();
      marker.setMap(this.map);
      this.showSearchPanel = false;
    },
  },

};
</script>

<style>
#map-container{
  position:absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 1;
}
#search_bar{
  display: flex;
  width: 25%;
  position: relative;
  top: 0;
  left: 0;
  z-index: 2;
  background-color: #e7eaec00;
}
#panel{
  display: inherit;
  width: 25%;
  position: relative;
  z-index: 2;
}
#result{
  top: 40px;
  left: 0;
  right: 0;
  background: #e1f3fbe0;
  /*border: 1px solid #ccc;*/
  border-top: none;
  overflow-y: auto;
  max-height: 400px; /* 控制搜索面板最大高度，超出部分显示滚动条 */
}
#operation{
  background-color: #e1f3fbe0;
  width: 100%;
  display: flex;
  justify-content: space-between;
}
</style>
