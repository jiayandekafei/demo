z350_J9U9203A_list.sub('doc_main',
function($common, p, subKey) {

	var _pu = {libName :
		'アプリテンプレート/メインドキュメント'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);
	_pu.sub = $common.subCreator(
		{}, [$common, {p : p, pu : _pu, pr : _pr, co : _co}]);

	//ドキュメント画面のタイトル
	_pu.title = p.pr.title;

	//インポートアクション永続化前処理
	_pu.beforeImportWrite = p.pr.importer(_pu, 'before');

//---------------ライブラリー内共通---------------
    _pr.setField = $common.fieldSetter();

	//削除済みかどうか判定します
	_pr.isDeleted = function(res) {
		return res.getFieldValue('fddeleted') == '1';
	};

	//過去データかどうか判定します
	_pr.isArchived = function(res) {
		return res.getFieldValue('fdarchived') == '1';
	};

	//文書のステータスを判定します
	_pr.isStatus = function(res) {
		var status = res.getFieldValue('fdstatus');
		for (var i = 1; i < arguments.length; i++) {
			if (status == arguments[i]) {
				return true;
            } 
        }  
		return false;
	};
  
	//閲覧制限ありかどうか判定します
	_pr.isRestricted = function(res) {
		return res.getFieldValue('fdrestricted') == '1';
	};
  

//---------------ツールバーボタン---------------

	//ボタン制御
	_pu.button = function(bid) {
		return $common.enabledScript(
			_pu.button, bid, p.pu.isSys());
	};
	_pu.button.hint = 'ボタン制御';
	_pu.button['編集'] = function(res) {
		return !_pr.isDeleted(res) && p.pu.isDBA();
	};
	_pu.button['削除'] = p.pu.isDBA;
	_pu.button['公開'] = function(res) {
		return _pr.isStatus(res, '1', '2', '3') && p.pu.isDBA();
	};
	_pu.button['過去データ'] = function(res) {
		return !_pr.isDeleted(res) && p.pu.isDBA();
	};
	_pu.button['コピー'] = p.pu.isCreator;

	//ボタン名
	_pu.buttonName = function(bid) {
		return $common.enabledScript(_pu.buttonName, bid);
	};
	_pu.buttonName.hint = 'ボタン名';
	_pu.buttonName['削除'] = function(res) {
		return !_pr.isDeleted(res) ? '削除' : '削除取消';
	};
	_pu.buttonName['公開'] = function(res) {
		return _pr.isStatus(res, '3') ? '公開' : '非公開';
	};
	_pu.buttonName['過去データ'] = function(res) {
		return !_pr.isArchived(res) ?
			'過去データへ' : '過去データ取消';
	};

	//ボタンアイコン
	_pu.buttonIcon = function(bid) {
		return $common.enabledScript(_pu.buttonIcon, bid);
	};
	_pu.buttonIcon.hint = 'ボタンアイコン';
	_pu.buttonIcon['削除'] = function(res) {
		return !_pr.isDeleted(res) ? 'ico-delete' : 'ico-revision';
	};
	_pu.buttonIcon['公開'] = function(res) {
		return _pr.isStatus(res, '3') ?
			'ico-revision-apply' : 'ico-declined';
	};
	_pu.buttonIcon['過去データ'] = function(res) {
		return !_pr.isArchived(res) ? 'ico-move' : 'ico-revision';
	};

	//確認メッセージ
	_pu.confirmMessage = function(bid) {
		var buttonName = z350_COMMON.getParamValue('buttonName');
		//スマートフォンの場合　初めて文書を開く時、buttonNameは存在しません
		return buttonName ? [
			'文書を',
			$common.enabledScript(
				_pu.confirmMessage,
				buttonName
			),
			'します。よろしいですか？'
		].join('') : '';
	};
	_pu.confirmMessage.hint = '確認メッセージ';
	_pu.confirmMessage.modify = function(res) {
		return !_pr.isDeleted(res) ? '削除' : '削除取消';
	};
	_pu.confirmMessage.modify2 = function(res) {
		return _pr.isStatus(res, '3') ? '公開' : '非公開';
	};
	_pu.confirmMessage.modify3 = function(res) {
		return !_pr.isArchived(res) ?
			'過去データに' : '過去データ取消';
	};

//---------------ボタンアクション---------------

	//独自更新アクション実行前処理
	_pu.beforeModifyWrite = function(res) {
		res = res || resource;
		var bid = z350_COMMON
			.getParamValue('buttonName');
		var funs = {
			modify : _pr.toggle.deleted,
			modify2 : _pr.toggle.status,
			modify3 : _pr.toggle.archived
		};
        var ret = funs[bid](res);
		res.save();
	};
	_pr.toggle = function(res, fid, toggle) {
		toggle = toggle || function(val) {
			return String(-val + 1);
		};
		var field = res.getField(fid);
		field.setValue(toggle(field.getStringValue()));
	};
	_pr.toggle.deleted = function(res) {
		return _pr.toggle(res, 'fddeleted');
	};
	_pr.toggle.status = function(res) {
		return _pr.toggle(res, 'fdstatus', function(status) {
			return status == '3' ? '2' : '3';
		});
	};
	_pr.toggle.archived = function(res) {
		return _pr.toggle(res, 'fdarchived');
	};

	//添付ファイルサイズを設定します
	_pu.setFileSize = function(res) {
		var count = 0, png = 0;
		var endsWith = function(title, suffixes) {
			return suffixes.find(function(suffix) {
				return title.endsWith(suffix);
			});
		};
		$common.eachFile(res, 'fdattachment', function(file) {
			var suffix = endsWith(
				file.getFieldValue('title').toUpperCase(),
				['.PNG', '.GIF']
			);
			if (!suffix || suffix == '.PNG') {
				png++;
				if (!suffix) {
					count++;
				}
			}
		});
        _pr.setField(res, [
            ['pffilecount', count],
            ['pffilecount_png', png]
        ]);
	};

	//ACLを設定します
	_pu.setAcl = function(res) {
		if (_pr.isRestricted(res)) {
			res.setAclAsPattern(p.co.acl.doc_main);
		} else {
			res.setAclAsRule(
				res.getSchema().getDefaultAcl());
        }  
	};

	//閲覧者を設定します
	_pr.setReaders = function(res) {
		_pr.setField(res, [[
			'fdreader',
			$common.getToUsers(
				res,
				[
					'fdreader_selected_u',
					'fdreader_selected_g'
				],
				p.pu.sub('set_range').getReaders(
					res.getField('fdrange_rids')
						.getStringArrayValue())
			)
		]]);
	}; 

//---------------フィールド表示・編集可否---------------

	//表示可否
	_pu.isVisible = function() {
		return $common.fieldScript(
			_pu.isVisible, p.pu.isSys());
	};
	_pu.isVisible.hint = '表示';

    _pu.isVisible.fdattachment =
	_pu.isVisible.fdrestricted = true;
	_pu.isVisible.fdrange_rids = function(res) {
		return _pr.isRestricted(res) && p.pr.isModifyAction();
	};
	_pu.isVisible.fdrange_names = function(res) {
		return _pr.isRestricted(res) && p.pr.isViewAction();
	};
	_pu.isVisible.fdreader_selected_u =
	_pu.isVisible.fdreader_selected_g = _pr.isRestricted;
  
	//編集可否
	_pu.isEditable = function() {
        var ret = false;
        if(p.pr.isModifyAction()) {
            ret = $common.fieldScript(
                      _pu.isEditable, p.pu.isSys());
        }
        return ret;
	};
	_pu.isEditable.hint = '編集';

	_pu.isEditable.fdattachment =
	_pu.isEditable.fdrestricted = true;
	_pu.isEditable.fdrange_rids =
		_pu.isVisible.fdrange_rids;
	_pu.isEditable.fdrange_names =
		_pu.isVisible.fdrange_names;
	_pu.isEditable.fdreader_selected_u =
		_pu.isVisible.fdreader_selected_u;
	_pu.isEditable.fdreader_selected_g =
		_pu.isVisible.fdreader_selected_g;

//---------------フィールドデフォルト、式と選択項目---------------

	//デフォルト
	_pu.defaultValue = function() {
		return $common.fieldScript({
            hint : 'デフォルト',
            fddba : p.pr.getDBARid,
            fdauditor : p.pr.getAuditorRid,
            fdtrandocid : _pr.getResId,			            //文書ID
            fdtranivdocid : _pr.getDocId			        //文書ID(移行用)
        });
	};
  
    //文書IDの取得処理
	_pr.getResId = function(res) {
        var ret = res.getFieldValue('id');
        return ret;
	};
  
    //文書ID(移行用)の取得処理
	_pr.getDocId = function(res) {
        var docId = res.getFieldValue('id');
        var ret = docId;
        var arr = util.naString(docId.split('-'));
        if(arr.length == 2 && arr[0].length() > 15){
          ret = arr[1];
        }
        return ret;
	};  

//---------------アクション---------------
  
	//表示アクション読込前処理
	_pu.beforeView = function(res){
		res = res || resource;
		//currentUserIdを持つユーザーがプリリソースに閲覧権がない場合に異常をスローしています
		$common.isAppResReadable(res);
	};	
  
	//作成アクション読込前処理
	_pu.beforeCreate = function(res) {
		res = res || resource;
		var source = z350_COMMON
			.getParamValue('sourceResourceId');
		if (source && (source =
			util.getResource(source, p.co.rootToken)
		)) {
			$common.copyToDest(source, res);
        }  
	};
  
	//編集アクション検証処理
	_pu.editInspect = function(res) {
		res = res || resource;
		var errors = $HM();
		return errors.isEmpty() ? null : errors;
	};  
  
	//編集アクション永続化前処理
	_pu.beforeEditWrite = function(res) {
		res = res || resource;
        _pr.setReaders(res);
        //閲覧範囲名を設定します
		res.getField('fdrange_names').setArrayValue(
			res.getField('fdrange_rids').getStringArrayValue()
				.map(function(rid) {
					var mRes = util.getResource(
						rid, p.co.rootToken);
					return !mRes ? '' : mRes
						.getFieldValue('title');
				})
		);
	};
  
	//編集アクション永続化後処理
	_pu.afterEditWrite = function(res) {
		res = res || resource;
		if (!ariel.global.isPrivate()) {
			_pu.setAcl(res);
        }  
	};

	//ワークフローアクション永続化後処理
	_pu.afterSignalWrite = function(res) {
		res = res || resource;
		_pu.setAcl(res);
	}; 
  
    //子要素独自更新アクション更新前処理
    _pu.prepareChildrenModifyWrite = function(res) {
        res = res || resource;
        var exa = z350_COMMON.getParamValue('exa');
        //添付ファイル数設定
        if(exa == 'opt-fileset') {
            _pu.setFileSize(res);
            res.save(p.co.saveOption);
            return;
        //移動  
        }else if(exa == 'opt-move') {
            var appRes = z350_COMMON.getAppRes(res);
            var archiveApp = util.getResource(appRes.getFieldValue('movetoarchres'), p.co.rootToken);
            if(archiveApp){
                res.moveTo(archiveApp);
            }
            return;  
        //削除  
        }else if(exa == 'opt-alldel') {
            _pr.setField(res, [
                ['fddeleted', '1']
            ]); 
        //削除取消  
        }else if(exa == 'opt-alldelcel') {
            _pr.setField(res, [
                ['fddeleted', '0']
            ]);
        }
        res.save();
    };  
	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});