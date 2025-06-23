<div class="container-fluid">
    <div class="row">
        <%-- サイドバーをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold">成績参照</div>