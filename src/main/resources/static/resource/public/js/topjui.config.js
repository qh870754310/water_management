/**
 * 配置文件说明
 * @type {string}
 *
 * ctx: 在本demo中，用于跨域请求远程服务器数据的网址，
 * 在实际应用中，大部分情况下topjui.all.min.js与应用程序在同一个域下，设置为空即可
 *
 * topJUI.config.mainPagePath: 系统主页面路径，不包含域名端口及参数，
 * 如果主页面访问的地址为http://localhost:8080/，则此处填写："/"
 * 如果主页面访问的地址为http://localhost:8080/index.html，则此处填写："/index.html"
 * 如果主页面访问的地址为http://localhost:8080/index.html?param=123，则此处填写："/index.html"
 * 如果主页面访问的地址为http://localhost:8080/topjui/，则此处填写："/topjui/"
 * 如果主页面访问的地址为http://localhost:8080/topjui/index.html?param=123，则此处填写："/topjui/index.html"
 *
 * topJUI.config.authUrl: 权限控制Url，请求后台判断用户是否有权限操作某个窗体或链接，
 * 后台返回true为有权限，false为无权限，不填表示不进行权限控制
 *
 * topJUI.language: 消息提示框的中文提示，可根据情况调整
 *
 */

/* 静态演示中获取contextPath，动态演示非必须 开始 */
var contextPath = "";
var firstPathName = window.location.pathname.split("/")[1];
if (!(firstPathName == "html" || firstPathName == "json" || firstPathName == "topjui")) {
    contextPath = "/" + firstPathName;
}
/* 静态演示中获取contextPath，动态演示非必须 结束 */
var ctx = "http://localhost:8080";
var myConfig = {
    config: {
        pkName: 'id', //数据表主键名，用于批量提交数据时获取主键值
        singleQuotesParam: false, //是否对批量提交表格选中记录的参数值使用单引号，默认为false，true:'123','456'，false:123,456
        datagrid: {
            page: 'page', //提交到后台的显示第几页的数据
            size: 'rows', //提交到后台的每页显示多少条记录
            total: 'total', //后台返回的总记录数参数名
            rows: 'rows' //后台返回的数据行对象参数名
        },
        postJson: false, //提交表单数据时以json或x-www-form-urlencoded格式提交，true为json，false为urlencoded
        appendRefererParam: false, //自动追加来源页地址上的参数值到弹出窗口的href或表格的url上，默认为false不追加
        statusCode: {
            success: 0, //执行成功返回状态码
            failure: -1 //执行失败返回状态码
        }
    },
    language: {
        message: {
            title: {
                operationTips: "操作提示",
                confirmTips: "确认提示"
            },
            msg: {
                success: "操作成功",
                failed: "操作失败",
                error: "未知错误",
                checkSelfGrid: "请先勾选中要操作的数据前的复选框",
                selectSelfGrid: "请先选中要操作的数据",
                selectParentGrid: "请先选中主表中要操作的一条数据",
                permissionDenied: "对不起，你没有操作权限",
                confirmDelete: "你确定要删除所选的数据吗？",
                confirmMsg: "确定要执行该操作吗？"
            }
        },
        edatagrid: {
            destroyMsg: {
                norecord: {
                    title: '操作提示',
                    msg: '没有选中要操作的记录！'
                },
                confirm: {
                    title: '操作确认',
                    msg: '确定要删除选中的记录吗？'
                }
            }
        }
    },
    //l: '7615362dd34bbdc5f5253950522749c00590731f6aa51d6baeeeff24af5ee1cb6dec2dbdec361c90af39ae6d4803f078c043353da784a4a08f200efc27acbadba4d237cbca4a1bdff1dcc11b333b572e4bd8fca98c295a94e7c1afbf7de5579c'
    l: 'aa0214b905fbede75d800de249bfe158301d4c671edd5ada4b3bd6a3e0788af85afa275ad4190d65dc8936ba526323564d1860103124341a836e52f5d23ac21d5ecb69f6d610270af9bbf758487713e61effc5d4668d1a44844015afecf743502ffd9d995ffafc906d06397731b2d00845ef9dbe'
};