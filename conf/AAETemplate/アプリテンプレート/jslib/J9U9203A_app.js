const z350_J9U9203A_list = function(global) {

	var _pu = {libName : 'アプリテンプレート'};
	var _pr = {};
	var _co = {};
	var $common = global.z350_KEIJIBAN;
	var $$ = $common.debuggerLoader(true);
	_pu.sub = $common.subCreator(
		{}, [$common, {pu : _pu, pr : _pr, co : _co}]);

//--------------------consts--------------------

	_co.rootId = ariel.consts.ROOT_USER_ID;
	_co.rootToken = ariel.global.getRootToken();

	_co.acl = {
		master : '/atypes-acl/J9U9203AWC/master_kanrisha',
		doc_main : '/atypes-acl/J9U9203A/doc_main'
	};

	_co.saveOption = java.util.EnumSet.of(
		com.arielnetworks.agn.atypes.SaveOption
			.WITHOUT_MODIFIER,
		com.arielnetworks.agn.atypes.SaveOption
			.WITHOUT_MODIFIED,
		com.arielnetworks.agn.atypes.SaveOption
			.WITHOUT_HISTORY
	);

	_co.sid = {
		app : 'J9U9203A-list',								
		doc_main : 'J9U9203A',
		doc_print : 'J9U9203AEE',
		doc_renrakusaki : 'J9U9203AKL',
		set_link : 'J9U9203AYL',
		set_range : 'J9U9203AQ0',
		set_renrakusaki : 'J9U9203AWC',
        set_mail : 'J9U9203AIE',
        doc_main_hide : 'J9U9203AKO'
	};
	_co.view = {
		doc_main : 'view',
		set_link : 'view-tr6ik4of',
		set_range : 'view-pukr1rxd',
		set_renrakusaki : 'view-vb018ozu',
        set_mail : 'view-a163glak'
	};
    _co.linkName = {
        'viewother3' : 'applink1',
        'viewother4' : 'applink2',
        'viewother5' : 'applink3',
        'viewother6' : 'manyuaruid'
    };
   _co.btnName = {
        'viewother3' : 'btnnm1',
        'viewother4' : 'btnnm2',
        'viewother5' : 'btnnm3'
    };
    var _ca = _co.sid['app'];
//--------------------field--------------------
  
	//選択項目
	_pu.options = function() {
		return [['option', $common.fieldScript({
			hint : '選択項目',
            'applink1' : $common.getAppResIdList,    // 関連アプリ１選択
            'applink2' : $common.getAppResIdList,    // 関連アプリ２選択
            'applink3' : $common.getAppResIdList     // 関連アプリ３選択
		})]];
	};
//--------------------role--------------------

	//システム管理者であるかどうか判定します
	_pu.isSys = function() {
		return util.isInSystemGroup(currentUserId);
	};
	//currentUserが指定したロールに所属するか判定します
	_pr.isInRole = function(method, res) {
		return _pu.isSys() || util.isUserContain(
			$AL([new java.lang.Integer(_pr[method](res))]),
			currentUserId
		);
	};
	//ロールIDを取得します
	_pr.getRoleId = function(fid, res) {
		return _pr.getApp(res).getField(fid).getIntValue();
	};
	//[DBA]ロールに所属するか判定します
	_pu.isDBA = function(res) {
		return _pr.isInRole('getDBARid', res);
	};
	//[DBA]ロールIDを取得します
	_pr.getDBARid = function(res) {
		return _pr.getRoleId('fddba', res);
	};
	//[Creator]ロールに所属するか判定します
	_pu.isCreator = function(res) {
		return _pr.isInRole('getCreatorRid', res);
	};
	//[Creator]ロールIDを取得します
	_pr.getCreatorRid = function(res) {
		return _pr.getRoleId('fdcreator', res);
	};
	//[Auditor]ロールに所属するか判定します
	_pu.isAuditor = function(res) {
		return _pr.isInRole('getAuditorRid', res);
	};
	//[Auditor]ロールIDを取得します
	_pr.getAuditorRid = function(res) {
		return _pr.getRoleId('fdauditor', res);
	};
	//[Reader]ロールに所属するか判定します
	_pu.isReader = function(res) {
		return _pr.isInRole('getReaderRid', res);
	};
	//[Reader]ロールIDを取得します
	_pr.getReaderRid = function(res) {
		return _pr.getRoleId('fdreader', res);
	};

//--------------------common--------------------

	//ドクリソースIDパラメータ
	_pu.paramDocResId = function () {
		return [
			'docResId=' + resource.getId(),
			'appResId=' + resource
				.getFieldValue('container')
		].join('&');
	};

	//アプリリソースIDパラメータ
	_pu.paramAppResId = function() {
		return 'appResId=' + resource.getId();
	};

    //他リソース作成ボタン対象リソース
    _pu.targetRes = function(btnName) {
        var rid = '';
        // 「関連アプリリンクボタン」の場合用
        if(btnName) {
            rid = _pr.getAppFieldValue(resource, _co.linkName[btnName]);
            rid = rid != '' ? rid : _co.sid['doc_renrakusaki'];
        } else {
            // 「他リソース作成」ボタンの場合用
            rid = _pr.getAppId();
        }
        return _pr.targetRes(rid);
    };
  
	//各設定ボタンターゲットリソース
	_pu.targetSettingRes = function(name) {
		return _pr.targetRes($common
			.getSetting(_co.sid[name]).getId());
	};
  
    //文書横幅
	_pu.getDocWidth = function() {
		var appRes = _pr.getApp();
		var docWidth = appRes.getFieldValue('docwidth');
		var result = docWidth + 'px';
		return result; 
	};

	//文書の開き方
	_pu.getDocOpenMode = function() {
        return $common.cache(_ca, 'getDocOpenMode', function() {
            var appRes = _pr.getApp();
            var docOpenMode = 'overlap';  //オーバーラップ表示
            if(appRes) {
                docOpenMode = appRes.getFieldValue('docopenmode');
            }
            return docOpenMode;
        });
	};
  
    //ボタン使用不可表示
    _pu.buttonFuka = function() {
        var ret = 'disable';   //無効時は無効状態で表示
        if(_pu.isAuditor() && !_pu.isDBA()) {
            ret = 'invisible'; //無効時は表示しない
        }
        return ret;
    };
  
//--------------------protected--------------------

	//編集モードかどうか判定します
	_pr.isModifyAction = function() {
		return util.actionType(rawAction).isModifyAction();
	};

	//読込モードかどうか判定します
	_pr.isViewAction = function() {
		return util.actionType(rawAction).isViewAction();
	};

	//指定されたフィールドが空かどうか判定します
	_pr.isNull = function(res, fids) {
		if (Array.isArray(fids)) {
			return !fids.find(function(fid) {
				return !_pr.isNull(res, fid);
			});
        }  
		else {
			var vals = res.getField(fids).getStringArrayValue();
			return vals.length == 0 ||
				(vals.length == 1 && vals[0] == '');
		}
	};

	//nullまたは空文字列を判定します
	_pr.isNullStr = function(str) {
		return !str || str == '';
	};

	//フィールドの値を取得します
	_pr.getValue = function(fid) {
		return global.ru ? ru.stringValue(fid)
			: global.resource ? resource
				.getFieldValue(fid) : '';
	};

	//アプリIDを取得します
	_pr.getAppId = function(res) {
		return z350_COMMON.getParamValue('appResId') ||
			$common.getApplication(res || resource).getId();
	};

	//アプリリソースを取得します
	_pr.getApp = function(res) {
		var ret = z350_COMMON.getParamValue('appResId');
		return ret ? util.getResource(ret, _co.rootToken)
			: res || global.resource ? $common.getApplication(res || resource) : null;
	};

	//仮想リソース作成時、元のドキュメントリソースを取得します
	_pr.getDoc = function() {
		return global.resource ? global.resource
			: util.getResource(
				z350_COMMON.getParamValue('docResId'),
				_co.rootToken
			);
	};

	//アプリのタイトルを取得します
	_pr.appTitle = function() {
		return _pr.getApp().getFieldValue('title');
	};

	//アプリリのURLを取得します
	_pr.appUrl = function(res) {
		var props = util.getProps();
		return [
			props.get('serverBaseUrl'),
			props.get('aquaDocumentRoot'),
			_pr.getAppId(),
			'/view'
		].join('');
	};

	//対象リソース
	_pr.targetRes = function(rid) {
		var ret = $HM();
		ret.put('fixed:' + rid, '1');
		return ret;
	};

	//インポータを作成します
	_pr.importer = function(lib, method) {
		return function(res) {
			res = res || resource;
			var importer = lib.sub('import');
			if (importer) {
				return importer[method](res);
            }  
		};
	};

	//ドキュメント画面のタイトル
	_pr.title = function(sid) {
		return _pr.getText((ariel.util.isString(sid) ?
				resourceLoader.getSchema(
					sid, _co.rootToken)
				: resource.getSchema()
			).getTitle());
	};

	//プロパティファイルで保存されているテキストを取得します
	_pr.getText = function(key) {
		var ret = util.getText(key, ariel.user.getLocale());
		return _pr.isNullStr(ret) ? key : ret;
	};
    
    // 指定なIDのリソースを取得する
    _pr.getResById = function(id, token) {
        return util.getResource(id, token || _co.rootToken);
    };
  
    // アプリには指定なフィールドの値を取得する
    _pr.getAppFieldValue = function(app, fid) {
        app = app || _pr.getApp();
        var ret = app.getFieldValue(fid);
        return ret ? ret : '';
    };  
    // ボタン名前を取得する 
    _pu.buttonLinkName = function(bid) {
        return _pr.linkBtnName(resource, bid);
     };

    // 関連アプリボタン有効・無効
    _pu.viewLinkButton = function(bid) {
        return _pr.btnValidate(resource, bid);
    }; 

    //関連アプリボタン名の取得する
    _pr.linkBtnName = function(res, bid) {
        var app = _pr.getResById(
            _pr.getAppFieldValue(res, _co.linkName[bid]),
            currentToken);
        var btnName = _pr.getAppFieldValue(res, _co.btnName[bid]);
        return !app ? '関連アプリを選択してください'
            : _pr.isNullStr(btnName) ? _pr.getAppFieldValue(app, 'title')
            : btnName;
    };

    // 関連アプリボタン有効・無効
    _pr.btnValidate = function(res, bid) {
        var appId = _pr.getAppFieldValue(res, _co.linkName[bid]);
        return appId != '';
    };
  
    //文書作成完了時、ブラウザ上部のメッセージを『非表示』にする
	_pr.messageClear = function() {
		var AquaConst = Packages.com.arielnetworks.agn.application.core.AquaConst;
		param.context.put(AquaConst.O_NOTIFY_MESSAGE, '');
	};  
  
//----------ビューのSQL----------  
    _pu.sql = function(action) {
        const publicSql = " fddeleted != '1' ";
        const deletedSql = " fddeleted = '1' ";
            return $common.sqlScript({
              'view' : publicSql,                     //デフォルトビュー
              'view-5xgxbsaw' : deletedSql            //削除済文書  
        }, action);
    };

	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

}(this);