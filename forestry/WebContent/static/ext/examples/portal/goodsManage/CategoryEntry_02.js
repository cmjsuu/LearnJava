// 种类信息录入
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();

	var fatherCategoryNameStore = Ext.create('Ext.data.JsonStore', {
		proxy : {
			type : 'ajax',
			url : appBaseUri + '/sys/category/getFatherCategoryName',
			reader : {
				type : 'json',
				root : 'list',
				idProperty : 'ItemValue'
			}
		},
		fields : [ 'ItemText', 'ItemValue' ]
	});
Ext.define('Forestry.app.goodsManage.CategoryEntry', {
	extend : 'Ext.panel.Panel',
	style : 'padding:5px;',
	bodyStyle : 'width:400px;overflow-x:auto; overflow-y:scroll',
	initComponent : function() {
		var me = this;
		me.dataId = me.dataId == undefined ? 0 : me.dataId;

		Ext.apply(this, {
			loadUrl : appBaseUri + '/sys/category/getCategoryById',
			// title : '树木种类信息录入',
			width : 350,
			xtype : 'form',
			layout : 'anchor',
			bodyPadding : '10 0',
			ui : 'light',
			items : [ {
				id : 'category-cmd',
				name : "cmd",
				xtype : "hidden",
				value : 'new'
			}, {
				id : 'category-id',
				name : 'id',
				xtype : 'hiddenfield'
			}, {
				id : 'category-name',
				name : 'name',
				xtype : 'textfield',
				fieldLabel : '种类名称',
				allowBlank : false,
				anchor : '96%',
				maxLength : 90,
				maxLengthText : '长度不能超过90'
			}, {
				id : 'category-front_desc',
				name : 'front_desc',
				xtype : 'textfield',
				fieldLabel : '种类名称描述',
				allowBlank : false,
				anchor : '96%',
				maxLength : 200,
				maxLengthText : '长度不能超过200'
			},{
				id : 'category-banner_url',
				name : 'banner_url',
				xtype : 'textfield',
				fieldLabel : 'banner栏图片地址',
				allowBlank : true,
				anchor : '96%',
				maxLength : 200,
				maxLengthText : '长度不能超过200'
			},{
				id : 'category-img_urlc',
				name : 'img_url',
				xtype : 'textfield',
				fieldLabel : '种类图片地址',
				allowBlank : true,
				anchor : '96%',
				maxLength : 200,
				maxLengthText : '长度不能超过200'
			},{
				id : 'category-wap_banner_url',
				name : 'wap_banner_url',
				xtype : 'textfield',
				fieldLabel : 'wapbanner图片地址',
				allowBlank : true,
				anchor : '96%',
				maxLength : 200,
				maxLengthText : '长度不能超过200'
			},{
				xtype:'radiogroup', name:'radiogroup', fieldLabel:'是否有父类',   
                columnWidth:0.3,items: [  
                    { boxLabel: '有', name: 'rb', inputValue: 1 ,id: 'radiogroup',checked: true},   
                    { boxLabel: '无', name: 'rb', inputValue: 2 } 
                ],
                listeners:{
                    'change':function(group,checked){  
                    	var fun = Ext.getCmp('father-name');
                    	var value = Ext.getCmp('radiogroup').getGroupValue()
                    	 if(value == 1){
                             fun.enable();
                         }else{
                             fun.disable();
                         }
                    		}  
                	}  
			},{
				id : 'father-name',
				xtype : 'combobox',
				fieldLabel : '所属父类名称',
				name : 'name',
				store : fatherCategoryNameStore,
				valueField : 'ItemValue',
				displayField : 'ItemText',
				typeAhead : true,
				queryMode : 'remote',
				emptyText : '请选择...',
				allowBlank : false,
				editable : false,
				listeners : {
					select : function(combo, record, index) {
						Ext.getCmp("categoryEntryForm-parentId").setValue(combo.getValue());
					}
				}
			}, {
				xtype : 'hiddenfield',
				id : 'categoryEntryForm-parentId',
				name : 'parent_id'
			}],
			
			buttons : [ '->', {
				text : '保存',
				iconCls : 'icon-save',
				handler : function(btn, eventObj) {
					var name = Ext.getCmp('category-name').getValue();
					var cmd = Ext.getCmp('category-cmd').getValue();
					var id = Ext.getCmp('category-id').getValue();
					var front_desc = Ext.getCmp('category-front_desc').getValue();
					var parent_id = Ext.getCmp('categoryEntryForm-parentId').getValue();;
					var banner_url = Ext.getCmp('category-banner_url').getValue();
					var img_url = Ext.getCmp('category-img_url').getValue();
					var wap_banner_url = Ext.getCmp('category-wap_banner_url').getValue();
					
					
					if ("" == name) {
						globalObject.errTip('名称不能为空！');
						alter(appBaseUri);
						return;
					}
		
					Ext.Ajax.request({
						url : appBaseUri + '/sys/category/saveCategory',
						params : {
							cmd : cmd,
							id : id,
							name : name,
							front_desc : front_desc,
							parent_id : parent_id,
							banner_url : banner_url,
							img_url : img_url,
							wap_banner_url : wap_banner_url
						},
						method : "POST",
						success : function(response) {
							var result = eval("(" + response.responseText + ")");
							Ext.getCmp('category-id').setValue(result.id);
							Ext.getCmp('category-cmd').setValue('edit');
							globalObject.msgTip('操作成功！');
						},
						failure : function(response) {
							globalObject.errTip('操作失败！');
						}
					});
				}
			}, {
				text : '保存并新增',
				iconCls : 'icon-add',
				hidden : me.dataId != undefined && me.dataId != '',
				handler : function(btn, eventObj) {
					var name = Ext.getCmp('category-name').getValue();
					var cmd = Ext.getCmp('category-cmd').getValue();
					var id = Ext.getCmp('category-id').getValue();
					var front_desc = Ext.getCmp('category-front_desc').getValue();
					var parent_id = Ext.getCmp('categoryEntryForm-parentId').getValue();;
					var banner_url = Ext.getCmp('category-banner_url').getValue();
					var img_url = Ext.getCmp('category-img_url').getValue();
					var wap_banner_url = Ext.getCmp('category-wap_banner_url').getValue();
					if ("" == name) {
						globalObject.errTip('名称不能为空！');
						return;
					}
					Ext.Ajax.request({
						url : appBaseUri + '/sys/category/saveCategory',
						params : {
							cmd : cmd,				
							name : name,
							front_desc : front_desc,
							parent_id : parent_id,
							banner_url : banner_url,
							img_url : img_url,
							wap_banner_url : wap_banner_url
						},
						method : "POST",
						success : function(response) {
							Ext.getCmp('category-id').setValue(null);
							Ext.getCmp('category-cmd').setValue('new');
							Ext.getCmp('category-name').setValue(null);
							Ext.getCmp('category-front_desc').setValue(null);
							Ext.getCmp('category-banner_url').setValue(null);
							Ext.getCmp('category-img_url').setValue(null);
							Ext.getCmp('category-wap_banner_url').setValue(null);
							globalObject.msgTip('操作成功！');
						},
						failure : function(response) {
							globalObject.errTip('操作失败！');
						}
					});
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					globalObject.closeTab(this.listTabId);
				}
			}, '->' ]
		});

		this.callParent(arguments);
		this.on('boxready', function() {
			if (me.loadUrl && me.dataId != 0) {
				// me.getForm().waitMsgTarget = me.getEl();
				Ext.Ajax.request({
					url : me.loadUrl,
					params : {
						'id' : me.dataId
					},
					waitMsg : '数据载入中，请稍候...',
					method : "POST",
					success : function(response) {
						var result = eval("(" + response.responseText + ")");
						Ext.getCmp('category-id').setValue(result.id);
						Ext.getCmp('category-cmd').setValue('edit');
						Ext.getCmp('category-name').setValue(result.name);
						Ext.getCmp('category-front_desc').setValue(result.front_desc);
					},
					failure : function(response) {
						globalObject.errTip('加载数据失败！');
					}
				});
			}
		});
	},
	onDestroy : function() {
		if (Ext.getCmp('ext-comp-1033')) {
			Ext.getCmp('ext-comp-1033').getStore().reload();
		}
	}
});
});