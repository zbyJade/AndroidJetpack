/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zc.androidjetpack.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zc.androidjetpack.bean.NetBean;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.bean.SortBean;
import com.zc.androidjetpack.bean.SubSortBean;
import com.zc.androidjetpack.utils.ParamsUtil;

import java.util.ArrayList;
import java.util.List;

import static com.zc.androidjetpack.utils.ParamsUtil.sorts;

/**
 * Create by KunMinX at 19/10/29
 */
public class HttpRequestManager {

    private static HttpRequestManager sRequestManager = new HttpRequestManager();

    private HttpRequestManager() {
    }

    public static HttpRequestManager getInstance() {
        return sRequestManager;
    }

    public void getNetList(int page, int size, HttpSuccessListCallback<NetBean.DataBean> callback) {
        OkGo.<NetBean>get(APIS.NetListUrl)
                .params(ParamsUtil.Page, page)
                .params(ParamsUtil.Size, size)
                .execute(new HttpJsonCallback<NetBean>() {
                    @Override
                    public void onSuccess(Response<NetBean> response) {
                        super.onSuccess(response);
                        callback.onSuccessResultList(response.body().getData(), response.body().getTotalRowsCount());
                    }
                });
    }

    public void getTestList(HttpSuccessListCallback<Shoe> callback) {
        ArrayList<Shoe> shoeBeans = new ArrayList<>();
        for (int i = 0; i < ParamsUtil.SIZE; i++) {
            Shoe shoeBean = new Shoe();
            shoeBean.setShoeName("shoe-");
            shoeBeans.add(shoeBean);
        }
        callback.onSuccessResultList(shoeBeans, ParamsUtil.SIZE * 10);
    }

    public void getSubSorts(HttpSuccessListCallback<SubSortBean> callback) {
        ArrayList<SubSortBean> subSortBeans = new ArrayList<>();
        for (int i = 0; i < ParamsUtil.SIZE; i++) {
            SubSortBean subSortBean = new SubSortBean();
            subSortBean.setSubSortName(sorts[0] + "-" + i);
            subSortBeans.add(subSortBean);
        }
        callback.onSuccessResultList(subSortBeans, ParamsUtil.SIZE);
    }

    public List<SortBean> getSorts() {
        ArrayList<SortBean> sortBeans = new ArrayList<>();
        for (int i = 0; i < sorts.length; i++) {
            SortBean sortBean = new SortBean();
            sortBean.setSortName(sorts[i]);
            sortBean.setSortId(i + "");
            sortBean.setSelected(i == 0);
            sortBeans.add(sortBean);
        }
        return sortBeans;
    }

    public interface HttpSuccessListCallback<T> {

        void onSuccessResultList(List<T> valueList, int totalCount);
    }

    public interface HttpSuccessBeanCallback<T> {

        void onSuccessResultBean(T value);

    }
}
