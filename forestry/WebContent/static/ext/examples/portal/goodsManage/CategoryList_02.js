// 树木种类信息列表
Ext.define('App.CategoryListWindow', {
	extend : 'Ext.window.Window',
	constructor : function(config) {
		config = config || {};
		Ext.apply(config, {
			title : '图书种类信息',
			width : 700,
			height : 570,
			bodyPadding : '10 5',
			// maximizable: true,
			modal : true,
			layout : 'fit',
			items : [ {
				xtype : 'form',
				fieldDefaults : {
					labelAlign : 'left',
					labelWidth : 40
				},
				items : [ {
					name : 'name',
					xtype : 'textfield',
					fieldLabel : '种类名称',
					readOnly : true,
					anchor : '100%'
				}, {
					xtype : 'htmleditor',
					name : 'front_desc',
					fieldLabel : '描述',
					readOnly : true,
					anchor : '100%',
					fontFamilies : [ 'Arial', 'Courier New', 'Tahoma', 'Times New Roman', 'Verdana', '宋体', '黑体', '隶书', '楷体_GB2312' ],
					height : 430,
					plugins : [ Ext.create('Ext.ux.custom.ImageHtmlEditor') ]
				} ],
				buttons : [ '->', {
					text : '确定',
					iconCls : 'icon-accept',
					width : 80,
					handler : function() {
						this.up('window').close();
					}
				}, '->' ]
			} ]
		});
		App.CategoryListWindow.superclass.constructor.call(this, config);
	}
});

Ext.define('Forestry.app.goodsManage.CategoryList', {
	extend : 'Ext.ux.custom.GlobalGridPanel',
	id : 'categoryList-grid',
	region : 'center',
	initComponent : function() {
		var me = this;
		
		Ext.define('ModelList', {
			extend : 'Ext.data.Model',
			idProperty : 'id',
			fields : [ {
				name : 'id',
				type : 'int'
			}, 'name', 'front_desc', 'banner_url','img_url','wap_banner_url' ]
		});

		var store = me.createStore({
			modelName : 'ModelList',
			proxyUrl : appBaseUri + '/sys/category/getCategory',
			proxyDeleteUrl : appBaseUri + '/sys/category/deleteCategory',
			extraParams : me.extraParams
		});

		var filters = {
			ftype : 'filters',
			encode : true,
			filters : [ {
				type : 'int',
				dataIndex : 'id'
			}, {
				type : 'string',
				dataIndex : 'name'
			}, {
				type : 'string',
				dataIndex : 'front_desc'
			}, {
				type : 'string',
				dataIndex : 'banner_url'
			}, {
				type : 'string',
				dataIndex : 'img_url'
			}, {
				type : 'string',
				dataIndex : 'wap_banner_url'
			} ]
		}

		var columns = [ {
			text : "ID",
			dataIndex : 'id',
			width : '5%'
		}, {
			text : "种类名称",
			dataIndex : 'name',
			width : '15%'
		}, {
			text : "描述",
			dataIndex : 'front_desc',
			width : '68%',
			sortable : false
		}, {
			text : "banner图片",
			dataIndex : 'banner_url',
			hidden : true
		}, {
			text : "图片",
			dataIndex : 'img_url',
			hidden : true
		}, {
			text : "wapbanner图片",
			dataIndex : 'wap_banner_url',
			hidden : true
		}, {
			xtype : 'actioncolumn',
			width : '8%',
			items : [ {
				iconCls : 'icon-view',
				disabled : !globalObject.haveActionMenu(me.cButtons, 'View'),
				tooltip : '查看',
				handler : function(grid, rowIndex, colIndex) {
					var gridRecord = grid.getStore().getAt(rowIndex);
					var win = new App.CategoryListWindow({
						hidden : true
					});
					var form = win.down('form').getForm();
					form.loadRecord(gridRecord);
					win.show();
				}
			}, {
				iconCls : 'edit',
				disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
				tooltip : '修改',
				handler : function(grid, rowIndex, colIndex) {
					var selectedCategory = grid.getStore().getAt(rowIndex);
					// var review = store.findRecord('id', selectedForestryType.get('id'));
					dataId = selectedCategory.get('id');
					me.onEditClick();
				}
			}, {
				iconCls : 'icon-delete',
				disabled : !globalObject.haveActionMenu(me.cButtons, 'Delete'),
				tooltip : '删除',
				handler : function(grid, rowIndex, colIndex) {
					var entity = grid.getStore().getAt(rowIndex);
					singleId = entity.get('id');
					me.onDeleteClick();
				}
			} ]
		} ];

		Ext.apply(this, {
			store : store,
			// features : [ filters ],
			columns : columns
		});

		store.loadPage(1);

		this.callParent(arguments);
	},
	onAddClick : function() {
		var me = this;
		globalObject.openTab('addCategory', '添加书本种类', Ext.create('Forestry.app.goodsManage.CategoryEntry', {
			listTabId : me.getTabId()
		}));
	},
	onEditClick : function() {
		var me = this;
		if (mainTab.getComponent('tabeditcategory')) {
			mainTab.getComponent('tabeditcategory').destroy();
		}
		globalObject.openTab('editCategory', '修改书本种类', Ext.create('Forestry.app.goodsManage.CategoryEntry', {
			dataId : dataId,
			listTabId : me.getTabId()
		}));
	},
	onViewClick : function() {
		var grid = Ext.getCmp("categorylist-grid");
		var id = grid.getSelectionModel().getSelection()[0].get('id');
		var gridRecord = grid.getStore().findRecord('id', id);
		var win = new App.CategoryListWindow({
			hidden : true
		});
		var form = win.down('form').getForm();
		form.loadRecord(gridRecord);
		win.show();
	}
});