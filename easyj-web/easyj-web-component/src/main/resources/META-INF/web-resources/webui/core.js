/**
 * UI 组件核心库
 *
 * @author Gavin Hu
 * @create 2015/5/10
 */
$(document).ready(function(){

    /**
     * 注册应用上下文
     * @param path
     */
    $.contextPath = function(path){
        if(path) {
            $(document).data("contextPath", path);
        } else {
            return $(document).data("contextPath");
        }
    }

    /**
     * 注册或查找适配器
     * @param name
     * @param callback
     */
    $.adapter = function(name, callback) {
        if(name && callback) {
            $(document).data("adapter-" + name, callback);
        } else if(name) {
            return $(document).data("adapter" + name);
        }
    }

    /**
     * 注册或者查找表单校验器
     * @param name
     * @param rules
     */
    $.validateRules = function(name, rules) {
        if(name && rules) {
            $(document).data("validateRules-" + name, rules);
        } else {
            return $(document).data("validateRules-" + name);
        }
    }

    /**
     * UI 组件异步数据加载
     *
     * @param name
     * @parmm data
     * @param callback
     */
    $.loadData = function(name, data, callback) {
        if(name && callback) {
            var contextPath = $.contextPath();
            if(contextPath) {
                $.ajax({
                    dataType: "json",
                    data: data ? data : {},
                    url: contextPath + "/web/component.json",
                    headers: {
                        'UI-Component' : name
                    },
                    success : callback
                });
            } else {
                alert("Please use $.contextPath('xxx') set context path first!");
            }
        }
    }

    /**
     * 事件定义
     */
    $("li[component-event]").each(function(i){
        //
        var eventDefinition = $.parseJSON($(this).attr("component-event"));
        //
        var $source = $("*[component-name="+eventDefinition.source+"]");
        var $target = $("*[component-name="+eventDefinition.target+"]");
        //
        $source.on(eventDefinition.sourceEvent, function(_, event){
            //
            var eventAdapter = eventDefinition.source + "_" + eventDefinition.target + "_Adapter";
            if(eventDefinition.eventAdapter) {
                eventAdapter = eventDefinition.eventAdapter;
            }
            var callback = $.adapter(eventAdapter);
            if(callback) {
                $target.trigger(eventDefinition.targetEvent, callback(eventDefinition, event.data));
            } else {
                alert("Adapter " + eventAdapter + " not registered!");
            }
        });
    });

    /**
     * 检验定义
     */
    $("form[novalidate!='true']").each(function(i){
        //
        var $form = $(this);
        $form.validate();
        //
        $form.find("*[component-name]").each(function(){
            //
            var $element = $(this);
            //
            var rulesName = $element.attr("component-name");
            var rulesValue = {};
            // process internal rules
            var internalRulesValue = $.validateRules(rulesName);
            if(internalRulesValue) {
                $.extend(rulesValue, internalRulesValue);
            }
            // process external rules
            var attrRules = $element.metadata({type:"attr", name:"rules", single:"rules"});
            if(attrRules) {
                $.extend(rulesValue, attrRules);
            }
            var attrMessages = $element.metadata({type:"attr", name:"messages", single:"messages"});
            if(attrMessages) {
                $.extend(rulesValue, {messages : attrMessages});
            }
            // add rules
            $element.rules("add", rulesValue);
        });
    });
});