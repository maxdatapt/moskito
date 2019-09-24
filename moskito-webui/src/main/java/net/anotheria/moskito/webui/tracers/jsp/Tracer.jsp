<%@ page language="java" contentType="text/html;charset=UTF-8" session="true" %>
<%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags" %>
<%@ taglib prefix="mos" uri="http://www.moskito.org/inspect/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../../shared/jsp/Header.jsp" flush="false"/>
<%--
    This page displays the traces of a single tracer.
--%>
<section id="main">
    <jsp:include page="../../shared/jsp/Alerts.jsp"/>

    <div class="content">

        <div class="box">
            <div class="box-title">
                <h3 class="pull-left">
                    Tracer for ${tracer.producerId}, enabled: ${tracer.enabled}, current entries: ${tracer.entryCount}, total seen: ${tracer.totalEntryCount}.
                </h3>
            </div>
            <div class="box-content">
                <table class="table table-striped table-tree tree">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Date</th>
                        <th>Duration</th>
                        <th>Pre-Exec Calls</th>
                        <th>Call</th>
                    </tr>
                    </thead>
                    <tbody>
                    <ano:iterate name="traces" type="net.anotheria.moskito.webui.tracers.api.TraceAO" id="trace">
                        <tr data-level="0">
                            <td>
                                <div>
                                    <i class="minus">–</i><i class="plus">+</i><i class="vline"></i>
                                    <mos:deepLink href="mskShowJourneyCall?pJourneyName=${journeyName}&pTracedCallName=Trace-${trace.id}">Trace-${trace.id}</mos:deepLink>
                                </div>
                            </td>
                            <td>${trace.created}</td>
                            <td>${trace.duration}</td>
                            <td>${trace.elementCount}</td>
                            <td>${trace.call}</td>
                        </tr>
                        <ano:notEmpty name="trace" property="tags">
                            <tr>
                                <td colspan="5">
                                    <div class="paddner tracers-tags-container">
                                        <ul class="tags-list tracers-tags-list">
                                            <ano:iterate id="tag" name="trace" property="tags">
                                                <li>
                                                    <span class="tag-name"><ano:write name="tag" property="tagName"/>: </span><ano:write name="tag" property="tagValue"/>
                                                </li>
                                            </ano:iterate>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </ano:notEmpty>
                        <tr class="dump-list" data-level="1">
                            <td colspan="5">

                                <ul>
                                    <ano:iterate name="trace" property="elements" id="element" type="java.lang.StackTraceElement">
                                        <li>${element}</li>
                                    </ano:iterate>
                                </ul>
                            </td>
                        </tr>

                    </ano:iterate>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="../../shared/jsp/Footer.jsp" flush="false"/>

</section>
</body>
</html>


