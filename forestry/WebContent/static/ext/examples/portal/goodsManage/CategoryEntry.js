// 树木识别信息录入
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

	Ext.define('App.CategoryEntryWindow', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '种类信息',
				width : 350,
				height : 350,
				bodyPadding : '10 5',
				modal : true,
				layout : 'fit',
				items : [ {
					xtype : 'form',
					fieldDefaults : {
						labelAlign : 'left',
						labelWidth : 90,
						anchor : '100%'
					},
					items : [ {
						id : 'category-cmd',
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					},{
						name : "id",
						xtype : "hidden",
					},{
						id : 'category-name',
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '种类名称',
						maxLength : 100,
						allowBlank : false
					}, {
						id : 'category-front_desc',
						xtype : 'textfield',
						name : 'front_desc',
						fieldLabel : '种类描述',
						maxLength : 200,
						allowBlank : true
					},{
						id : 'category-banner_url',
						name : 'banner_url',
						xtype : 'textfield',
						fieldLabel : 'banner栏图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-img_urlc',
						name : 'img_url',
						xtype : 'textfield',
						fieldLabel : '种类图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-wap_banner_url',
						name : 'wap_banner_url',
						xtype : 'textfield',
						fieldLabel : 'wapbanner图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					}, {
						xtype:'radiogroup', name:'radiogroup', fieldLabel:'二级分类',   
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
		                        	 Ext.getCmp('categoryEntryForm-parentId').setValue(0);
		                             fun.disable();
		                         }
		                    		}  
		                	}  
					},{
						id : 'father-name',
						xtype : 'combobox',
						fieldLabel : '所属一级分类名称',
						name : 'name2',
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
					} ],
					buttons : [ '->', {
						id : 'categorywindow-save',
						text : '保存',
						iconCls : 'icon-save',
						width : 80,
						handler : function(btn, eventObj) {
							var window = btn.up('window');
							var form = window.down('form').getForm();
							if (form.isValid()) {
								window.getEl().mask('数据保存中，请稍候...');
								var vals = form.getValues();
								Ext.Ajax.request({
									url : appBaseUri + '/sys/category/saveCategory',
									params : {
										cmd : vals['cmd'],
										name : vals['name'],
										front_desc : vals['front_desc'],
										parent_id : vals['parent_id'],
										banner_url : vals['banner_url'],
										img_url : vals['img_url'],
										wap_banner_url : vals['wap_banner_url']
									},
									method : "POST",
									success : function(response) {
										globalObject.msgTip('操作成功！');
									},
									failure : function(response) {
										globalObject.errTip('操作失败！');
									}
								});
								window.getEl().unmask();
								window.close();
							}
						}
					}, {
						id : 'categorywindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'categorywindow-accept',
						text : '确定',
						hidden : true,
						iconCls : 'icon-accept',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, '->' ]
				} ]
			});
			App.CategoryEntryWindow.superclass.constructor.call(this, config);
		}
	});

	Ext.define('App.CategoryEntryWindow_02', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '种类信息',
				width : 350,
				height : 310,
				bodyPadding : '10 5',
				modal : true,
				layout : 'fit',
				items : [ {
					xtype : 'form',
					fieldDefaults : {
						labelAlign : 'left',
						labelWidth : 90,
						anchor : '100%'
					},
					items : [ {
						id : 'category-cmd',
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					}, {
						id : 'category-name',
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '种类名称',
						maxLength : 100,
						allowBlank : false
					},{
						id : 'category-parentname',
						xtype : 'textfield',
						name : 'parentCategoryName',
						fieldLabel : '一级分类名称',
						maxLength : 100,
						allowBlank : false
					}, {
						id : 'category-front_desc',
						xtype : 'textfield',
						name : 'front_desc',
						fieldLabel : '种类描述',
						maxLength : 200,
						allowBlank : true
					},{
						id : 'category-banner_url',
						name : 'banner_url',
						xtype : 'textfield',
						fieldLabel : 'banner栏图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-img_urlc',
						name : 'img_url',
						xtype : 'textfield',
						fieldLabel : '种类图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-wap_banner_url',
						name : 'wap_banner_url',
						xtype : 'textfield',
						fieldLabel : 'wapbanner图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					}],
					buttons : [ '->',  {
						id : 'categorywindow-accept',
						text : '确定',
						hidden : true,
						iconCls : 'icon-accept',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, '->' ]
				} ]
			});
			App.CategoryEntryWindow_02.superclass.constructor.call(this, config);
		}
	});
	Ext.define('App.CategoryEntryWindow_03', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '种类信息',
				width : 350,
				height : 350,
				bodyPadding : '10 5',
				modal : true,
				layout : 'fit',
				items : [ {
					xtype : 'form',
					fieldDefaults : {
						labelAlign : 'left',
						labelWidth : 90,
						anchor : '100%'
					},
					items : [ {
						id : 'category-cmd',
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					},{
						name : "id",
						xtype : "hidden",
					},{
						id : 'category-name',
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '种类名称',
						maxLength : 100,
						allowBlank : false
					}, {
						id : 'category-front_desc',
						xtype : 'textfield',
						name : 'front_desc',
						fieldLabel : '种类描述',
						maxLength : 200,
						allowBlank : true
					},{
						id : 'category-banner_url',
						name : 'banner_url',
						xtype : 'textfield',
						fieldLabel : 'banner栏图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-img_urlc',
						name : 'img_url',
						xtype : 'textfield',
						fieldLabel : '种类图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					},{
						id : 'category-wap_banner_url',
						name : 'wap_banner_url',
						xtype : 'textfield',
						fieldLabel : 'wapbanner图片地址',
						allowBlank : true,
						maxLength : 200,
						maxLengthText : '长度不能超过200'
					}, {
						xtype:'radiogroup', name:'radiogroup', fieldLabel:'二级分类',   
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
		                        	 Ext.getCmp('categoryEntryForm-parentId').setValue(0);
		                             fun.disable();
		                         }
		                    		}  
		                	}  
					},{
						id : 'father-name',
						xtype : 'combobox',
						fieldLabel : '所属一级分类名称',
						name : 'name2',
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
					} ],
					buttons : [ '->', {
						id : 'categorywindow-save',
						text : '保存',
						iconCls : 'icon-save',
						width : 80,
						handler : function(btn, eventObj) {
							var window = btn.up('window');
							var form = window.down('form').getForm();
							if (form.isValid()) {
								window.getEl().mask('数据保存中，请稍候...');
								var vals = form.getValues();
								Ext.Ajax.request({
									url : appBaseUri + '/sys/category/saveCategory',
									params : {
										cmd : vals['cmd'],
										id : vals['id'],
										name : vals['name'],
										front_desc : vals['front_desc'],
										parent_id : vals['parent_id'],
										banner_url : vals['banner_url'],
										img_url : vals['img_url'],
										wap_banner_url : vals['wap_banner_url']
									},
									method : "POST",
									success : function(response) {
										globalObject.msgTip('操作成功！');
									},
									failure : function(response) {
										globalObject.errTip('操作失败！');
									}
								});
								window.getEl().unmask();
								window.close();
							}
						}
					}, {
						id : 'categorywindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'categorywindow-accept',
						text : '确定',
						hidden : true,
						iconCls : 'icon-accept',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, '->' ]
				} ]
			});
			App.CategoryEntryWindow_03.superclass.constructor.call(this, config);
		}
	});
	Ext.define('Forestry.app.goodsManage.CategoryEntry', {
		extend : 'Ext.ux.custom.GlobalGridPanel',
		region : 'center',
		viewConfig: {
	        stripeRows:true,
	        forceFit: true,
	        scrollOffset: 0,
	        enableTextSelection:true,
	        expandOnly:true,

	        listeners:{
	            beforerender:function(tree, eOpts) {
	                tree.getStore().load();
	            }
	        },
	        getRowClass : function(record, rowindex, rowParams, store) {
	            var eCode = record.get('is_show');//标识出状态的那一列
	            if (eCode==0) {
	            	
	                return 'x-grid-record-warning';
	            }
	        }
		    },
		initComponent : function() {
			var me = this;

			Ext.define('ModelList', {
				extend : 'Ext.data.Model',
				idProperty : 'id',
				fields : [ {
					name : 'id',
					type : 'int'
				}, 'name','parentCategoryName', 'front_desc', 'banner_url','img_url','wap_banner_url','is_show']
			});

			var store = me.createStore({
				modelName : 'ModelList',
				proxyUrl : appBaseUri + '/sys/category/getCategory',
				proxyDeleteUrl : appBaseUri + '/sys/category/deleteCategory',
				extraParams : me.extraParams
			});

			var columns = [ {
				text : "ID",
				dataIndex : 'id',
				width : '6%'
			},{
				text : "is_show",
				dataIndex : 'is_show',
				width : '6%',
				hidden : true
			}, {
				text : "种类名称",
				dataIndex : 'name',
				width : '18%'
			},{
				text : "一级菜单名称",
				dataIndex : 'parentCategoryName',
				width : '18%'
			}
			, {
				text : "种类描述",
				dataIndex : 'front_desc',
				width : '16%'
			}, {
				text : "banner图片地址",
				dataIndex : 'banner_url',
				width : '15%'
			}, {
				text : "种类图片地址",
				dataIndex : 'img_url',
				width : '15%'
			}, {
				text : "wapbanner地址",
				dataIndex : 'wap_banner_url',
				width : '16%',
			}, {
				xtype : 'actioncolumn',
				width : '10%',
				items : [ {
					iconCls : 'icon-pictures',
					tooltip : '种类图片地址',
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						new Ext.window.Window({
							title : '图片',
							width : 340,
							height : 390,
							bodyPadding : '10 5',
							modal : true,
							autoScroll : true,
							items : [ {
								html :	"<img src="+ entity.get('wap_banner_url') +" />"
							} ]
						}).show();
					}
				}, {
					iconCls : 'icon-view',
					tooltip : '查看',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'View'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.CategoryEntryWindow_02({
							hidden : true
						});
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);						
						form.findField('name').setReadOnly(true);	
						form.findField('parentCategoryName').setReadOnly(true);	
						form.findField('front_desc').setReadOnly(true);
						form.findField('banner_url').setReadOnly(true);
						form.findField('img_url').setReadOnly(true);
						form.findField('wap_banner_url').setReadOnly(true);					
						Ext.getCmp('categorywindow-accept').show();
						win.show();
					}
				}, {
					iconCls : 'edit',
					tooltip : '修改',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.CategoryEntryWindow_03({
							hidden : true
						});
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField("cmd").setValue("edit");
						win.show();
					}
				}, {
					iconCls : 'icon-delete',
					tooltip : '删除',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Delete'),
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						singleId = entity.get('id');
						me.onDeleteClick();
					}
				} ]
			} ];

			Ext.apply(this, {
				id : 'categorygrid',
				store : store,
				columns : columns
			});

			store.loadPage(1);
			
		

			this.callParent(arguments);
		},
		onAddClick : function() {
			new App.CategoryEntryWindow().show();
		},
		onViewClick : function() {
			var grid = Ext.getCmp("categorygrid");
			var id = grid.getSelectionModel().getSelection()[0].get('id');
			var gridRecord = grid.getStore().findRecord('id', id);
			var win = new App.CategoryEntryWindow({
				hidden : true
			});
			var form = win.down('form').getForm();
			form.loadRecord(gridRecord);
			form.findField('id').setReadOnly(true);
			form.findField('name').setReadOnly(true);	
			form.findField('front_desc').setReadOnly(true);
			form.findField('banner_url').setReadOnly(true);
			form.findField('img_url').setReadOnly(true);
			form.findField('wap_banner_url').setReadOnly(true);
			Ext.getCmp('forestryentrywindow-save').hide();
			Ext.getCmp('forestryentrywindow-cancel').hide();
			Ext.getCmp('forestryentrywindow-accept').show();
			win.show();
		}
	});
});