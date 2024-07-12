<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
  <div class="overlay"></div>
  <div class="card"></div>
</div>


<style>
  .container {
    width: 220px; height: 310px; transition : all 0.1s;
    position: relative;
  }
  .overlay {
    position: absolute;
    width: 220px;
    height: 310px;
    background: linear-gradient(105deg,
    transparent 40%,
    rgba(255, 219, 112, 0.8) 45%,
    rgba(132, 50, 255, 0.6) 50%,
    transparent 54%);
    filter: brightness(1.1) opacity(0.8);
    mix-blend-mode: color-dodge;
    background-size: 150% 150%;
    background-position: 100%;
    transition: all 0.1s;
  }
  .card {
    width: 220px; height: 310px;
    background-image: url(pika.webp);
    background-size: cover;
  }
</style>

<script src="/blog/js/Minji.js"></script>
<%@ include file="layout/footer.jsp"%>