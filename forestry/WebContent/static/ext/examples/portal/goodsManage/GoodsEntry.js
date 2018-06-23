// 树木识别信息录入
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();

	var fatherCategoryNameStore = Ext.create('Ext.data.JsonStore', {
		proxy : {
			type : 'ajax',
			url : appBaseUri + '/sys/category/getCategoryName',
			reader : {
				type : 'json',
				root : 'list',
				idProperty : 'ItemValue'
			}
		},
		fields : [ 'ItemText', 'ItemValue' ]
	});

	Ext.define('App.GoodsEntryWindow', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '商品信息',
				width : 350,
				height : 450,
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
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					}, {
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '书名',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_author_value',
						fieldLabel : '作者',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'retail_price',
						fieldLabel : '价格',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'goods_number',
						fieldLabel : '当前库存',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_ISBN_value',
						fieldLabel : 'ISBN',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textarea',
						name : 'goods_brief',
						fieldLabel : '书本简介',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'goods_desc',
						fieldLabel : '书本图片',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'attribute_pagenum_value',
						fieldLabel : '页码',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_publishHouse_value',
						fieldLabel : '出版社',
						maxLength : 200,
						allowBlank : false
					},{
						id : 'father-name',
						xtype : 'combobox',
						fieldLabel : '所属分类名称',
						name : 'father-name',
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
						name : 'categroy_id'
					} ],
					buttons : [ '->', {
						id : 'goodswindow-save',
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
									url : appBaseUri + '/sys/goods/saveGoods',
									params : {
										cmd : vals['cmd'],
										name : vals['name'],					
										attribute_author_value : vals['attribute_author_value'],
										retail_price : vals['retail_price'],
										goods_number : vals['goods_number'],
										attribute_ISBN_value : vals['attribute_ISBN_value'],
										goods_brief : vals['goods_brief'],
										goods_desc : vals['goods_desc'],
										attribute_pagenum_value : vals['attribute_pagenum_value'],
										attribute_publishHouse_value : vals['attribute_publishHouse_value'],
										category_id : vals['categroy_id']
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
						id : 'goodswindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'goodswindow-accept',
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
			App.GoodsEntryWindow.superclass.constructor.call(this, config);
		}
	});
	Ext.define('App.GoodsEntryWindow_2', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '商品信息',
				width : 350,
				height : 450,
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
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					}, {
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '书名',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_author_value',
						fieldLabel : '作者',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'retail_price',
						fieldLabel : '价格',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'goods_number',
						fieldLabel : '当前库存',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_ISBN_value',
						fieldLabel : 'ISBN',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textarea',
						name : 'goods_brief',
						fieldLabel : '书本简介',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'goods_desc',
						fieldLabel : '书本图片',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'attribute_pagenum_value',
						fieldLabel : '页码',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_publishHouse_value',
						fieldLabel : '出版社',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'category_name',
						fieldLabel : '图书分类',
						maxLength : 200,
						allowBlank : false
					} ],
					buttons : [ '->',{
						id : 'goodswindow-accept',
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
			App.GoodsEntryWindow_2.superclass.constructor.call(this, config);
		}
	});
	
	Ext.define('App.GoodsEntryWindow_3', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '商品信息',
				width : 350,
				height : 450,
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
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					},{
						name : "id",
						xtype : "hidden",
					}, {
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '书名',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_author_value',
						fieldLabel : '作者',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'retail_price',
						fieldLabel : '价格',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'goods_number',
						fieldLabel : '当前库存',
						maxLength : 100,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_ISBN_value',
						fieldLabel : 'ISBN',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textarea',
						name : 'goods_brief',
						fieldLabel : '书本简介',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'goods_desc',
						fieldLabel : '书本图片',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'numberfield',
						name : 'attribute_pagenum_value',
						fieldLabel : '页码',
						maxLength : 200,
						allowBlank : false
					},{
						xtype : 'textfield',
						name : 'attribute_publishHouse_value',
						fieldLabel : '出版社',
						maxLength : 200,
						allowBlank : false
					},{
						id : 'father-name',
						xtype : 'combobox',
						fieldLabel : '所属分类名称',
						name : 'father-name',
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
						name : 'categroy_id'
					} ],
					buttons : [ '->', {
						id : 'goodswindow-save',
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
									url : appBaseUri + '/sys/goods/saveGoods',
									params : {
										cmd : vals['cmd'],
										id : vals['id'],
										name : vals['name'],					
										attribute_author_value : vals['attribute_author_value'],
										retail_price : vals['retail_price'],
										goods_number : vals['goods_number'],
										attribute_ISBN_value : vals['attribute_ISBN_value'],
										goods_brief : vals['goods_brief'],
										goods_desc : vals['goods_desc'],
										attribute_pagenum_value : vals['attribute_pagenum_value'],
										attribute_publishHouse_value : vals['attribute_publishHouse_value'],
										category_id : vals['categroy_id']
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
						id : 'goodswindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'goodswindow-accept',
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
			App.GoodsEntryWindow_3.superclass.constructor.call(this, config);
		}
	});
	Ext.define('Forestry.app.goodsManage.GoodsEntry', {
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
	            var eCode = record.get('is_on_sale');//标识出状态的那一列
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
				}, 'goods_sn', 'name','category_name','goods_brief','goods_number','attribute_author_value', 'attribute_ISBN_value','goods_desc','retail_price','attribute_pagenum_value','attribute_publishHouse_value','is_on_sale' ]
			});

			var store = me.createStore({
				modelName : 'ModelList',
				proxyUrl : appBaseUri + '/sys/goods/getGoods',
				proxyDeleteUrl : appBaseUri + '/sys/goods/deleteGoods',
				extraParams : me.extraParams
			});

			var columns = [ {
				text : "ID",
				dataIndex : 'id',
				width : '6%'
			}, {
				text : "is_on_sale",
				dataIndex : 'is_on_sale',
				width : '18%',
				hidden : true
			},{
				text : "goods_sn",
				dataIndex : 'goods_sn',
				width : '18%'
			}, {
				text : "名称",
				dataIndex : 'name',
				width : '16%'
			},{
				text : "图书分类",
				dataIndex : 'category_name',
				width : '16%'
			},{
				text : "商品简介",
				dataIndex : 'goods_brief',
				width : '16%'
			},{
				text : "商品数量",
				dataIndex : 'goods_number',
				width : '16%'
			},{
				text : "商品价格",
				dataIndex : 'retail_price',
				width : '16%'
			},{
				text : "页码",
				dataIndex : 'attribute_pagenum_value',
				width : '16%'
			},{
				text : "出版社",
				dataIndex : 'attribute_publishHouse_value',
				width : '16%'
			}, {
				text : "作者",
				dataIndex : 'attribute_author_value',
				width : '15%'
			}, {
				text : "ISBN",
				dataIndex : 'attribute_ISBN_value',
				width : '15%'
			}, {
				text : "图片地址",
				dataIndex : 'goods_desc',
				width : '16%',
				sortable : false,
				hidden : true
			},{
				xtype : 'actioncolumn',
				width : '10%',
				items : [ {
					iconCls : 'icon-pictures',
					tooltip : '图片地址',
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						new Ext.window.Window({
							title : '图片',
							width : 340,
							height : 440,
							bodyPadding : '10 5',
							modal : true,
							autoScroll : true,
							items : [ {
								html : entity.get('goods_desc')
							} ]
						}).show();
					}
				},{
					iconCls : 'icon-view',
					tooltip : '查看',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'View'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.GoodsEntryWindow_2({
							hidden : true
						});
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField('name').setReadOnly(true);
						form.findField('goods_number').setReadOnly(true);
						form.findField('goods_brief').setReadOnly(true);
						form.findField('attribute_author_value').setReadOnly(true);
						form.findField('attribute_ISBN_value').setReadOnly(true);
						form.findField('retail_price').setReadOnly(true);
						form.findField('goods_desc').setReadOnly(true);
						form.findField('attribute_pagenum_value').setReadOnly(true);
						form.findField('attribute_publishHouse_value').setReadOnly(true);
						Ext.getCmp('goodswindow-accept').show();
						win.show();
					}
				}, {
					iconCls : 'edit',
					tooltip : '修改',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.GoodsEntryWindow_3({
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
				id : 'goodsentrygrid',
				store : store,
				columns : columns
			});

			store.loadPage(1);

			this.callParent(arguments);
		},
		onAddClick : function() {
			new App.GoodsEntryWindow().show();
		},
		onViewClick : function() {
			var grid = Ext.getCmp("goodsentrygrid");
			var id = grid.getSelectionModel().getSelection()[0].get('id');
			var gridRecord = grid.getStore().findRecord('id', id);
			var win = new App.GoodsEntryWindow({
				hidden : true
			});
			var form = win.down('form').getForm();
			form.loadRecord(gridRecord);
			form.findField('name').setReadOnly(true);
			form.findField('goods_brief').setReadOnly(true);
			form.findField('attribute_author_value').setReadOnly(true);
			form.findField('attribute_ISBN_value').setReadOnly(true);
			form.findField('goods_desc').setReadOnly(true);
			Ext.getCmp('goodswindow-save').hide();
			Ext.getCmp('goodswindow-cancel').hide();
			Ext.getCmp('goodswindow-accept').show();
			win.show();
		}
	});
});