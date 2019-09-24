<%@ page language="java" contentType="text/html;charset=UTF-8" session="true" %>
<%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../../shared/jsp/Header.jsp" flush="false"/>
<section id="main">
    <jsp:include page="../../shared/jsp/Alerts.jsp"/>

    <div class="content">
        <ano:notPresent name="killSwitchConfiguration">
            <div class="box">
                <div class="box-content paddner">
                    Remote version doesn't support killswitch yet.
                </div>
            </div>
        </ano:notPresent>

        <ano:present name="killSwitchConfiguration">

            <ano:define id="killSwitchConfiguration"
                        name="killSwitchConfiguration"
                        type="net.anotheria.moskito.core.config.KillSwitchConfiguration"/>

            <div class="box">
                <div class="box-content paddner align-center-flex">
                    <div>
                        <input type="checkbox" class="js-switch-color"
                               data-switcher="metricCollection"
                            ${killSwitchConfiguration.disableMetricCollection ? 'checked' : ''}/>
                    </div>

                    <ano:iF test="${killSwitchConfiguration.disableMetricCollection}">
                        <span class="tag-box">Metric collection disabled. Toggle to enable.</span>
                    </ano:iF>

                    <ano:iF test="${not killSwitchConfiguration.disableMetricCollection}">
                        <span class="tag-box">Metric collection enabled. Toggle to disable.</span>
                    </ano:iF>
                </div>
            </div>

            <div class="box">
                <div class="box-content paddner align-center-flex">
                    <div>
                        <input type="checkbox" class="js-switch-color"
                               data-switcher="tracing"
                            ${killSwitchConfiguration.disableTracing ? 'checked' : ''}/>
                    </div>

                    <ano:iF test="${killSwitchConfiguration.disableTracing}">
                        <span class="tag-box">Tracing disabled. Toggle to enable.</span>
                    </ano:iF>

                    <ano:iF test="${not killSwitchConfiguration.disableTracing}">
                        <span class="tag-box">Tracing enabled. Toggle to disable.</span>
                    </ano:iF>
                </div>
            </div>
        </ano:present>
    </div>

    <jsp:include page="../../shared/jsp/Footer.jsp"/>

    <script>
        $(function () {
            var $switchEl = $('.js-switch-color');

            $switchEl.each(function (idx, el) {
                var switchery = new Switchery(el, {color: '#ff6a00', secondaryColor: '#64bd63'});
                $(el).data('switchery', switchery);
            });

            var StateHolder = (function () {
                var values = {
                    enabled: {
                        metricCollection: 'Metric collection disabled. Toggle to enable.',
                        tracing: 'Tracing disabled. Toggle to enable.'
                    },
                    disabled: {
                        metricCollection: 'Metric collection enabled. Toggle to disable.',
                        tracing: 'Tracing enabled. Toggle to disable.'
                    }
                };

                var nextStateValue = function (transitionName, currentState) {
                    var currentStateStr = currentState ? 'enabled' : 'disabled';
                    return values[currentStateStr][transitionName];
                };

                return {
                    nextStateValue: nextStateValue
                };
            })();

            $switchEl.change(function () {
                var $this = $(this);

                $this.data('switchery').disable();

                var data = {
                    name: $this.data('switcher'),
                    value: $this.prop('checked')
                };

                var nextStateValue = StateHolder.nextStateValue(data.name, data.value);
                $this.parent().next().text(nextStateValue);

                if (data.name === 'metricCollection') {
                    $('.alert.alert-danger').hide();
                }

                $.get("mskSwitchKillSetting", data).always(function () {
                    $this.data('switchery').enable();
                });
            });
        });
    </script>

</section>
</body>
</html>