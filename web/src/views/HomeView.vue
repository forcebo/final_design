<template>
  <NavBar />
  <ContentField>
    <div class="row justify-content-center">
      <div
        class="card"
        style="width: 1000px; border-radius: 0%; border-color: coral"
      >
        <div class="row" style="margin-top: 10px">
          <!-- 右侧内容 -->
          <div class="col-md-12">
            <div class="card" style="border-radius: 0%; border-color: coral">
              <div style="width: 100%">
                <div
                  id="carouselExampleIndicators"
                  class="carousel slide"
                  data-bs-ride="carousel"
                  style="width: 100%"
                >
                  <div class="carousel-indicators">
                    <button
                      type="button"
                      data-bs-target="#carouselExampleIndicators"
                      data-bs-slide-to="0"
                      class="active"
                      aria-current="true"
                      aria-label="Slide 1"
                    ></button>
                    <button
                      type="button"
                      data-bs-target="#carouselExampleIndicators"
                      data-bs-slide-to="1"
                      aria-label="Slide 2"
                    ></button>
                  </div>
                  <div class="carousel-inner">
                    <div class="carousel-item active">
                      <img
                        src="@/assets/images/轮播01.png"
                        class="d-block w-100"
                        alt="..."
                      />
                    </div>
                    <div class="carousel-item">
                      <img
                        src="@/assets/images/轮播02.png"
                        class="d-block w-100"
                        alt="..."
                      />
                    </div>
                  </div>
                  <!-- <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                  </button>
                  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                  </button> -->
                </div>
              </div>
              <div style="margin-top: 10px">
                <form @submit.prevent="searchTeacher">
                  <div class="input-group mb-3">
                    <div class="col-sm-2">
                      <div class="input-group">
                        <input
                          v-model="id"
                          type="text"
                          class="form-control"
                          id="age"
                          placeholder="老师编号"
                          style="
                            height: 30px;
                            font-size: x-small;
                            padding-left: 30px;
                          "
                        />
                        <i
                          class="bi bi-search"
                          style="
                            position: absolute;
                            left: 8px;
                            top: 6px;
                            cursor: pointer;
                          "
                        ></i>
                      </div>
                    </div>
                    <div class="col-sm-2">
                      <input
                        v-model="subject"
                        type="text"
                        class="form-control"
                        id="studentSubject"
                        placeholder="请输入求教科目"
                        style="height: 30px; font-size: x-small"
                      />
                    </div>
                    <div class="col-sm-2">
                      <select
                        v-model="area"
                        class="form-select"
                        id="area"
                        style="height: 30px; font-size: x-small"
                      >
                        <option value="">区域</option>
                        <option value="莲湖区">莲湖区</option>
                        <option value="新城区">新城区</option>
                        <option value="碑林区">碑林区</option>
                        <option value="雁塔区">雁塔区</option>
                        <option value="灞桥区">灞桥区</option>
                        <option value="未央区">未央区</option>
                        <option value="阎良区">阎良区</option>
                        <option value="临潼区">临潼区</option>
                        <option value="长安区">长安区</option>
                        <option value="高陵区">高陵区</option>
                        <option value="蓝田区">蓝田区</option>
                        <option value="户县区">户县区</option>
                        <option value="周至区">周至区</option>
                        <option value="高新区">高新区</option>
                        <option value="沣渭新区">沣渭新区</option>
                        <option value="曲江新区">曲江新区</option>
                        <option value="杨凌">杨凌</option>
                      </select>
                    </div>
                    <div class="col-sm-1">
                      <select
                        v-model="sex"
                        class="form-select"
                        id="sex"
                        style="height: 30px; font-size: x-small; width: 100px"
                      >
                        <option value="">性别</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                      </select>
                    </div>
                    <div class="col-sm-2" style="margin-left: 20px">
                      <select
                        v-model="school"
                        class="form-select"
                        id="school"
                        style="height: 30px; font-size: x-small"
                      >
                        <option value="">高校</option>
                        <option value="西安交通大学">西安交通大学</option>
                        <option value="西北工业大学">西北工业大学</option>
                        <option value="长安大学">长安大学</option>
                        <option value="陕西师范大学">陕西师范大学</option>
                        <option value="电子科技大学">电子科技大学</option>
                        <option value="西北大学">西北大学</option>
                        <option value="西安理工大学">西安理工大学</option>
                        <option value="西安科技大学">西安科技大学</option>
                        <option value="陕西科技大学">陕西科技大学</option>
                        <option value="西安工业大学">西安工业大学</option>
                        <option value="西安石油大学">西安石油大学</option>
                        <option value="西安工程大学">西安工程大学</option>
                        <option value="西安音乐学院">西安音乐学院</option>
                        <option value="西安外国语大学">西安外国语大学</option>
                        <option value="西安体育学院">西安体育学院</option>
                        <option value="西安美术学院">西安美术学院</option>
                      </select>
                    </div>
                    <div class="col-sm-1">
                      <select
                        v-model="education"
                        class="form-select"
                        id="education"
                        style="height: 30px; width: 110px; font-size: x-small"
                      >
                        <option value="">老师学历</option>
                        <option value="大专以下">大专以下</option>
                        <option value="大专在读">大专在读</option>
                        <option value="大专毕业">大专毕业</option>
                        <option value="本科在读">本科在读</option>
                        <option value="本科毕业">本科毕业</option>
                        <option value="硕士在读">硕士在读</option>
                        <option value="硕士毕业">硕士毕业</option>
                        <option value="博士在读">博士在读</option>
                        <option value="博士毕业">博士毕业</option>
                      </select>
                    </div>
                    <button
                      class="btn btn-outline-secondary"
                      type="submit"
                      id="button-addon2"
                      style="
                        height: 30px;
                        font-size: x-small;
                        margin-left: 40px;
                      "
                    >
                      检索
                    </button>
                  </div>
                </form>
              </div>
              <div
                class="mb-3 row align-items-center"
                style="margin-left: 5px; margin-right: 5px"
              >
                <div class="col-sm-4">
                  <div
                    style="
                      background-color: rgb(243, 227, 175);
                      font-size: x-small;
                    "
                  >
                    <img
                      src="https://xa.eduease.com/beijingjiajiao/i/d05.gif"
                      alt="图像"
                      style="margin-left: 10px"
                    />
                    <router-link
                      :to="{ path: '/tutor/search/' }"
                      class="type-style"
                      style="color: red; font-weight: bold; margin-left: 10px"
                      >高校分类</router-link
                    >
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安交通大学' },
                        }"
                        class="item-style"
                        >西安交大</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '长安大学' },
                        }"
                        class="item-style"
                        >长安大学</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '陕西师范大学' },
                        }"
                        class="item-style"
                        >陕西师大</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安电子科技大学' },
                        }"
                        class="item-style"
                        >电子科大</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西北工业大学' },
                        }"
                        class="item-style"
                        >西北工大</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西北大学' },
                        }"
                        class="item-style"
                        >西北大学</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安理工大学' },
                        }"
                        class="item-style"
                        >西安理工</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安工程大学' },
                        }"
                        class="item-style"
                        >西安工程</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安工业大学' },
                        }"
                        class="item-style"
                        >西安工业</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安科技大学' },
                        }"
                        class="item-style"
                        >西安科技</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安石油大学' },
                        }"
                        class="item-style"
                        >西安石油</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安音乐大学' },
                        }"
                        class="item-style"
                        >音乐学院</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-5">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安外国语大学' },
                        }"
                        class="item-style"
                        style="width: 85px"
                        >西安外国语大学</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安美术学院' },
                        }"
                        class="item-style"
                        >美术学院</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { school: '西安体育学院' },
                        }"
                        class="item-style"
                        >体育学院</router-link
                      >
                    </div>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div
                    style="
                      background-color: rgb(243, 227, 175);
                      font-size: x-small;
                    "
                  >
                    <img
                      src="https://xa.eduease.com/beijingjiajiao/i/d06.gif"
                      alt="图像"
                      style="margin-left: 10px"
                    />
                    <router-link
                      :to="{ path: '/tutor/search/' }"
                      class="type-style"
                      style="color: red; font-weight: bold; margin-left: 10px"
                      >科目分类</router-link
                    >
                  </div>
                  <div class="row">
                    <div class="col-sm-2">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '语文' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >语文</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '数学' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >数学</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '英语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >英语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '物理' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >物理</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '化学' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >化学</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '生物' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >生物</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '计算机' },
                        }"
                        class="item-style"
                        style="width: 38px"
                        >计算机</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '艺术' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >艺术</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '书法' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >书法</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '舞蹈' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >舞蹈</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '数据库' },
                        }"
                        class="item-style"
                        style="width: 38px"
                        >数据库</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-2">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '韩语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >韩语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '俄语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >俄语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '日语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >日语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '体育' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >体育</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '钢琴' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >钢琴</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '法语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >法语</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-2">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '编程' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >编程</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '武术' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >武术</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '德语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >德语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '口语' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >口语</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '书法' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >书法</router-link
                      >
                    </div>
                    <div class="col-sm-2" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { subject: '古琴' },
                        }"
                        class="item-style"
                        style="width: 25px"
                        >古琴</router-link
                      >
                    </div>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div
                    style="
                      background-color: rgb(243, 227, 175);
                      font-size: x-small;
                    "
                  >
                    <img
                      src="https://xa.eduease.com/beijingjiajiao/i/d05.gif"
                      alt="图像"
                      style="margin-left: 10px"
                    />
                    <router-link
                      :to="{ path: '/tutor/search/' }"
                      class="type-style"
                      style="color: red; font-weight: bold; margin-left: 10px"
                      >区域分类</router-link
                    >
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '莲湖区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >莲湖区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '新城区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >新城区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '碑林区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >碑林区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '雁塔区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >雁塔区</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '灞桥区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >灞桥区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '未央区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >未央区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '阎良区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >阎良区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '临潼区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >临潼区</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '长安区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >长安区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '高陵区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >高陵区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '蓝田区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >蓝田区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 0px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '户县区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >户县区</router-link
                      >
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-3">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '周至区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >周至区</router-link
                      >
                    </div>
                    <div class="col-sm-3" style="padding-left: 5px">
                      <router-link
                        :to="{
                          path: '/tutor/search/',
                          query: { area: '高新区' },
                        }"
                        class="item-style"
                        style="width: 40px"
                        >高新区</router-link
                      >
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-top: 20px">
                  <div class="col-md-8">
                    <div class="card" style="height: 200px; border-radius: 0%">
                      <div
                        class="recommend"
                        style="
                          font-size: small;
                          background-color: rgb(249, 232, 185);
                        "
                      >
                        <img
                          src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                          alt="图像"
                          style="margin-left: 10px"
                        />
                        <span style="font-weight: bold; margin-left: 5px"
                          >推荐教师</span
                        >
                      </div>
                      <table
                          class="table table-striped table-hover"
                          style="font-size: x-small"
                        >
                          <tbody>
                            <tr
                              v-for="teacher in recommend_teachers"
                              :key="teacher.id"
                              @click="getToDetail(teacher.id)"
                              style="cursor: pointer"
                            >
                              <td style="width: 20%">
                                <span style="color: green; font-weight: bold">{{
                                  teacher.realname.charAt(0) + "教员"
                                }}</span>
                              </td>
                              <td style="width: 10%">
                                <span style="color: green; font-weight: bold">{{teacher.sex}}</span>
                              </td>
                              <td style="width: 20%">
                                <span style="color: green; font-weight: bold">{{teacher.education}}</span>
                              </td>
                              <td style="width: 25%; color: darkgray">
                                {{ teacher.major }}
                              </td>
                              <td style="width: 25%">
                                <span>{{ teacher.school }}</span>
                              </td>
                              
                            </tr>
                          </tbody>
                        </table>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div
                      class="card"
                      style="
                        height: 200px;
                        border-radius: 0%;
                        width: 290px;
                        margin-left: 20px;
                      "
                    >
                      <div
                        class="recommend"
                        style="
                          font-size: small;
                          background-color: rgb(249, 232, 185);
                        "
                      >
                        <img
                          src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                          alt="图像"
                          style="margin-left: 10px"
                        />
                        <span style="font-weight: bold; margin-left: 5px"
                          >最新教师</span
                        >
                      </div>
                      <!-- 添加这个div以使表格能够滚动 -->
                      <div style="width: 100%">
                        <table
                          class="table table-striped table-hover"
                          style="font-size: x-small"
                        >
                          <tbody>
                            <tr
                              v-for="teacher in newTeachers"
                              :key="teacher.id"
                              @click="getToDetail(teacher.id)"
                              style="cursor: pointer"
                            >
                              <td style="width: 20%">
                                <span style="color: green; font-weight: bold">{{
                                  teacher.realname.charAt(0) + "教员"
                                }}</span>
                              </td>
                              <td style="width: 40%">
                                <span>{{ teacher.school }}</span>
                              </td>
                              <td style="width: 40%; color: darkgray">
                                {{ teacher.major }}
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "../components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
import router from "@/router";

export default {
  components: {
    NavBar,
    ContentField,
  },
  setup() {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      const role = localStorage.getItem("role");
      if (role === "STUDENT") {
        store.commit("updateStudentToken", jwt_token);
        store.dispatch("getStudentInfo", {
          success() {
            store.commit("updateStudentPullingInfo", false);
          },
          error() {
            store.commit("updateStudentPullingInfo", false);
          },
        });
      } else if (role === "TEACHER") {
        store.commit("updateTeacherToken", jwt_token);
        store.dispatch("getTeacherInfo", {
          success() {
            store.commit("updateTeacherPullingInfo", false);
          },
          error() {
            store.commit("updateTeacherPullingInfo", false);
          },
        });
      }
    } else {
      store.commit("updateStudentPullingInfo", false);
      store.commit("updateTeacherPullingInfo", false);
    }
    let id = ref("");
    let subject = ref("");
    let area = ref("");
    let sex = ref("");
    let school = ref("");
    let education = ref("");
    let limit = 5;
    let recommend_teachers = ref([]);
    let newTeachers = ref([]);

    const searchTeacher = () => {
      router.push({
        path: "/tutor/search/",
        query: {
          id: id.value,
          subject: subject.value,
          area: area.value,
          sex: sex.value,
          school: school.value,
          education: education.value,
        },
      });
    };

    const getRecommendTeachers = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/get/recommended/teachers/" + limit + "/",
        type: "get",
        success(resp) {
          if (resp.success == true) {
            recommend_teachers.value = resp.data;
            console.log(recommend_teachers.value);
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    };
    getRecommendTeachers();

    const getTeacherNew = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/get/teacher/new/",
        type: "get",
        success(resp) {
          if (resp.success == true) {
            newTeachers.value = resp.data;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }
    getTeacherNew();

    const getToDetail = (id) => {
      if (store.state.student.token != "" || store.state.teacher.token != "") {
        window.open('/tutor/detail/?id='+ id, '_blank');
      } else {
        router.push("/user/account/login/");
        alert("请先登录！");
      }
    }

    return {
      id,
      subject,
      area,
      sex,
      school,
      education,
      searchTeacher,
      recommend_teachers,
      newTeachers,
      getToDetail,
    };
  },
};
</script>

<style scoped>
.table-header {
  cursor: pointer;
  /* Optional: Change color to indicate it's clickable */
}

.table-header:hover {
  text-decoration: underline;
  color: coral; /* Optional: Add underline on hover */
}

.type-style {
  text-decoration: none;
}

.type-style:hover {
  text-decoration: underline;
}

.item-style {
  display: inline-block;
  width: 48px;
  font-size: x-small;
  color: chocolate;
  text-decoration: none; /* 移除下划线 */
  font-size: x-small; /* 设置字体大小 */
  cursor: pointer; /* 设置鼠标指针为手型 */
}
/* 鼠标悬停时的样式 */
.item-style:hover {
  background-color: coral; /* 设置背景颜色 */
  text-decoration: underline;
}

.recommond-table tr {
  cursor: pointer;
}
</style>

