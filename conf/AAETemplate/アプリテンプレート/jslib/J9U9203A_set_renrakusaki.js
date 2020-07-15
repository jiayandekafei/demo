z350_J9U9203A_list.sub('set_renrakusaki',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/連絡先設定情報'};
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

	//デフォルト
	_pu.defaultValue = function() {
		return $common.fieldScript({
			hint : 'デフォルト',
			title : p.pr.title,
			fdurl : p.pr.appUrl,
			fddba : p.pr.getDBARid
		});
	};

	//ボタン表示制御
	_pu.button = function(bid) {
		return $common.enabledScript({
			hint : 'ボタン表示',
			'設定' : _pr.btnSetting
		}, bid, false);
	};
	_pr.btnSetting = function() {
		return !_pr.getSetting() && (p.pu.isDBA() || p.pu.isSys());
	};
  
    //ボタン使用不可表示
    _pu.buttonFuka = p.pu.buttonFuka;

	//設定情報リソースを取得します
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

	//設定リソースのフィールド値を取得します
	_pu.getValue = function(fid, app) {
		var sRes = _pu.getSettingRes(app);
		return sRes && sRes.getFieldValue(fid) || '';
	};

	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});