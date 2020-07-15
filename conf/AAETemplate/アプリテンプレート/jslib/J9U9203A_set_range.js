z350_J9U9203A_list.sub('set_range',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/閲覧範囲'};
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

	//表示可否
	_pu.isVisible = function(fid) {
		return $common.fieldScript({
			hint : '表示',
			fddba : false
		}, p.pu.isSys());
	};

	//編集可否
	_pu.isEditable = _pu.isVisible;

	//デフォルト
	_pu.defaultValue = function() {
		return $common.fieldScript({
			hint : 'デフォルト',
			fdtitle : p.pr.title,
			fddba : p.pr.getDBARid
		});
	};
  
	//必須項目
	_pu.isRequired = function() {
		return !$common.fieldScript({
			hint : '必須項目',
			title : false,
			fdcode : false
		}, p.pu.isSys() || ariel.global.isImport());
	};

	//編集アクション検証処理
	_pu.editInspect = function(res) {
		res = res || resource;
		var errors = $HM();
        var fids = ['fdusers', 'fdgroups'];
        if (p.pr.isNull(res, fids)) {
            fids.forEach(function(fid) {
                errors.put(fid, 'Error');
            });
        }  
        if (_pr.codeExisted(res)) {
            errors.put('fdcode', 'Error');
        }  
		return errors.isEmpty() ? null : errors;
	};
	_pr.codeExisted = function(res) {
		var viewR;
		var filter = {'fdcode-eq' : res.getField('fdcode')
				.getStringArrayValue()};
		$common.eachViewRecs(function(viewRecord){
			viewR = viewRecord;
			return false;
		}, p.co.view[subKey], filter, p.pr.getApp(res));
		return !p.pr.isNullStr(viewR) &&
			viewR.getFieldValue('id') != res.getId();
	};

	//閲覧メンバー(閲覧ユーザーと閲覧グループ)を取得します
	_pu.getReaders = function(rids) {
		rids = (rids instanceof Array) ? rids : [rids];
		return rids.reduce(function(ret, rid) {
			return $common.getToUsers(
				!ariel.util.isString(rid) ? rid
					: util.getResource(rid, p.co.rootToken),
				['fdusers', 'fdgroups'],
				ret
			);
		}, new java.util.HashSet());
	};

	//コードで閲覧範囲レコードを取得します
	_pu.getReadersByCode = function(codes, app) {
		codes = (codes instanceof Array) ? codes : [codes];
		var list = new java.util.ArrayList();
		$common.eachViewRecs(function(viewRecord){
			list.add(viewRecord);
			return true;
		}, p.co.view[subKey], {}, app || p.pr.getApp());
		return _pu.getReaders(list.toArray().filter(function(rec) {
			var val = rec.getFieldValue('fdcode');
			return codes.find(function(code) {
				return code == val;
			});
		}));
	};

	//検索可否
	_pu.isSearchable = function() {
		return thisFieldId == 'title' || z350_COMMON
			.getParamValue('exa') != 'resource-select';
	};
	
	//インポートアクション永続化後処理
	_pu.afterImportWrite = function() {
		var sub = _pu.sub('import');
		if (sub && (typeof sub.afterImportWrite == 'function')) {
			sub.afterImportWrite();
        }  
	};
	
	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});