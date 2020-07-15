z350_J9U9203A_list.sub('set_link',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/関連リンク'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);

	//ドキュメント画面のタイトル
	_pu.title = p.pr.title;

	//デフォルト
	_pu.defaultValue = function() {
		return $common.fieldScript({
			hint : 'デフォルト',
			title : p.pr.title,
			fddba : p.pr.getDBARid
		});
	};

	//表示可否
	_pu.isVisible = function() {
		return $common.fieldScript({
			hint : '表示',
			fddba : false
		}, p.pu.isSys());
	};

	//編集可否
	_pu.isEditable = _pu.isVisible;

	//ボタン表示制御
	_pu.button = function(bid) {
		return $common.enabledScript({
			hint : 'ボタン表示',
			'設定' : _pr.btnSetting
		}, bid, false);
	};
	_pr.btnSetting = function() {
		return !_pr.getSetting(p.pr.getApp()) && (p.pu.isDBA() || p.pu.isSys());
	};
  
    //ボタン使用不可表示
    _pu.buttonFuka = p.pu.buttonFuka;
  
	//仮想リソース
	_pu.createTempRes = function() {
		return $common.createTempRes(
			p.co.sid[subKey],
			function(res) {
				var app = p.pr.getApp();
				if (app) {
					var setting = _pr.getSettingRes(app);
					if (setting) {
						$common.copyToDest(setting, res);  
                    }  
					else {
						res.getField('fddba').setArrayValue([]);
                    } 
                    var aclId = res.getSchema().getDefaultAcl();
                    res.setAclAsRule(aclId);
				}
			}
		);
	};

	//対象リソース
	_pu.targetRes = function() {
		return p.pr.targetRes(p.co.sid[subKey]);
	};

	//設定情報リソースを取得します
	_pr.getSettingRes = function(app) {
		return util.rcache().getOrApply(
			app.getId() + '/' + subKey + '/res',
			function() {
				var ret = _pr.getSetting(app);
				return ret ? $common.toRes(ret) : null;
			}
		);
	};
	_pr.getSetting = function(app) {
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

	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});