<template>
  <div id="map-container">
    <div id="search_bar" style="display: flex; ">
      <el-input type="text" v-model="searchValue" placeholder="请输入地址" />
      <el-button @click="search">搜索</el-button>
      <ul>
        <li v-for="result in searchResults" :key="result.location">
          <a href="#" @click="setCenter(result.location)">
            {{ result.name }} - {{ result.address }}
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script type="text/javascript">
  window._AMapSecurityConfig = {
    securityJsCode:'18f8ca190c9bf437f7a6c4af79719d62',
  }
</script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=522861cd1297e6da70e3562625801e71"></script>

<script>
export default {
  data() {
    return {
      map: null,
      searchValue: "",
      searchResults: []
    };
  },
  mounted() {
    const script = document.createElement('script')
    script.src = 'https://webapi.amap.com/maps?v=1.4.15&key=<522861cd1297e6da70e3562625801e71>'
    script.async = true
    script.onload = () => {
      // 高德地图 API 加载完成后执行初始化代码
      this.initMap()
    }
    document.head.appendChild(script);

  },
  methods: {
    initMap(){
      const map = new AMap.Map("map-container", {
        center: [118.81183, 32.03297], // 南京邮电大学经纬度
        zoom: 16,
        resizeEnable: true
      });
      this.map = map;

      AMap.plugin('AMap.Geolocation', () => {
        const geolocation = new AMap.Geolocation({
          enableHighAccuracy: true,
          timeout: 10000,
          zoomToAccuracy: true,
          buttonOffset: new AMap.Pixel(10, 20),
          buttonPosition: 'RB'
        });

        geolocation.getCurrentPosition((status, result) => {
          if (status === 'complete') {
            map.setCenter(result.position);
          }
        });
      });

      map.on("click", e => {
        this.setCenter(e.lnglat);
      });
    },

    setCenter(lnglat) {
      this.map.setCenter(lnglat);
    },
    search() {
      const keyword = this.searchValue.trim();
      if (keyword === "") return;

      const geocoder = new AMap.Geocoder({
        city: "南京"
      });
      geocoder.getLocation(keyword, (status, result) => {
        if (status === "complete" && result.info === "OK") {
          this.searchResults = result.geocodes;
          if (this.searchResults.length > 0) {
            const location = this.searchResults[0].location;
            this.setCenter(location);
          }
        } else {
          this.searchResults = [];
          alert("搜索失败，请输入更详细的地址");
        }
      });
    }
  }
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
