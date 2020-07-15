z350_J9U9203A_list.sub('set_mail',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/メールマスタ'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);
	_pu.sub = $common.subCreator(
		{}, [$common, {p : p, pu : _pu, pr : _pr, co : _co}]);
  
	//key:送信種類 , value:[メール送信, 件名, 本文]	
	_co.fid_mails = {
		'shouninn' : ['snirmail', 'snirtitle', 'snircontent'],				   //確認依頼 : [メール送信, 件名, 本文]
		'kakuninn' : ['snmail', 'sntitle', 'sncontent'],					   //確認 : [メール送信, 件名, 本文]
		'sashimodoshi' : ['smmail', 'smtitle', 'smcontent']					   //差戻し: [メール送信, 件名, 本文]	　　　　　　　　　　								   
	};
  
	//ドキュメント画面のタイトル
	_pu.title = p.pr.title;

	//インポートアクション永続化前処理
	_pu.beforeImportWrite = p.pr.importer(_pu, 'before');

	//デフォルト
	_pu.defaultValue = function() {
		return $common.fieldScript({
			hint : 'デフォルト',
			fddba : p.pr.getDBARid
		});
	};

	//ボタン表示制御
	_pu.button = function(bid) {
		return $common.enabledScript({
			hint : 'ボタン表示',
			'メールマスタ' : _pr.btnSetting
		}, bid, false);
	};
	_pr.btnSetting = function() {
		return !_pr.getSetting() && (p.pu.isDBA() || p.pu.isSys());
	};
  
	//メールマスタリソースを取得します
	_pu.getSettingRes = function(app) {
		app = app || p.pr.getApp();
		return util.rcache().getOrApply(
			app.getId() + '/' + subKey + '/res',
			function() {
				var ret = _pr.getSetting(app);
				return ret ? $common.toRes(ret) : null;
			}
		);
	};
	_pr.getSetting = function(app) {
		app = app || p.pr.getApp();
		return util.rcache().getOrApply(
			app.getId() + '/' + subKey + '/rec',
			function() {
				var ret = null;
				$common.eachViewRecs(function(viewRecord){
					ret = viewRecord;
					return false;
				}, p.co.view[subKey], {}, app);
				return ret;
			}
		);
	};
  
	//ボタン使用不可表示
	_pu.buttonFuka = p.pu.buttonFuka;

	//----------メイン文書用----------

	//メール送信情報の取得処理
	_pu.getMailInfo = function(res, type) {
		res = res || resource;
		var appRes = p.pr.getApp(res);
		var mailRes = _pu.getSettingRes(appRes);
		var ret = null;
		if(mailRes && _co.fid_mails[type]) {
			ret = {
				species : mailRes.getFieldValue(_co.fid_mails[type][0]) == 1,  
				title : mailRes.getFieldValue(_co.fid_mails[type][1]),
				body : mailRes.getFieldValue(_co.fid_mails[type][2])
			};
		}
		return ret;
	};	
  
	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});