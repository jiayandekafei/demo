z350_J9U9203A_list.sub('doc_renrakusaki',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/連絡先'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);

	//ドキュメント画面のタイトル
	_pu.title = p.pr.title;

	//仮想リソース
	_pu.createTempRes = function() {
		return $common.createTempRes(
			p.co.sid[subKey]);
	};

	//対象リソース
	_pu.targetRes = function() {
		return p.pr.targetRes(p.co.sid[subKey]);
	};

	//フィールドデフォルト
	_pu.defaultValue = function() {
		return thisFieldId == 'fdapptitle' ? p.pr.appTitle()
			: p.pu.sub('set_renrakusaki').getValue(thisFieldId);
	};

	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});