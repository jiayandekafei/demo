z350_J9U9203A_list.sub('scr_bgproc',
function($common, p, subKey) {

    var _pu = {libName :
        'アプリテンプレート/バックグラウンド処理'};
    var _pr = {};
    var _co = {};
    var $$ = $common.debuggerLoader(true);
    _pu.sub = $common.subCreator(
        {}, [$common, {p : p, pu : _pu, pr : _pr, co : _co}]);
    _pr.setField = $common.fieldSetter();

    _pu.bgProc = function(key) {
        return ({
            month : _pr.monthProc
        })[key]();
    };

	//月一回
	_pr.monthProc = function() {
		_pr.sakujo();
	};
  
    //削除
	_pr.sakujo = function() {
		_pr.doDelete(
			p.co.sid['doc_main'],
			{'fddeleted' : '1'},
			'modified',
			util.formatDate(
				util.nextMonth(util.today(), -1)),
			['削除']
		);
	};
	_pr.doDelete = function(sid, filter, fid, date, show) {
		show = show || [];
		_pr.proc(
			sid,
			filter,
			function(rec) {
                try{
                    var res = rec.getResource();
                    var val = util.formatDate(res.getField(fid).getDateValue());
                    if (val < date) {
                        show[1] = [res.getFieldValue('title'),
                            val].join(' | ');
                        if(sid == res.getType()) {
                            ariel.resource.removeResource(res, true);
                        }
                    }
                } catch(e) {
                    util.warn(show + 'エラー：' + e);
                    return false;
                }
				return true;
			}
		);
	};  

    _pr.proc = function(sid, filter, exe) {
        var schema = resourceLoader
              .getSchema(sid, ariel.global.getCurrentUserToken().as(ariel.consts.ROOT_USER_ID, userDir, groupDir));
        if(schema) {
          ariel.resource.eachBaseRecordsWithFilter(
              exe, sid, undefined, filter);
        } else {
          util.warn([
              '[エラー]',
              '無効なスキーマID',
              sid
          ].join('\n'));
        }
    }; 

    Object.freeze(_co);
    Object.freeze(_pr);
    return Object.freeze(_pu);

});