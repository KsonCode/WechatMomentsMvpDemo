package com.wechatmomentsmvpdemo.utils;

import com.wechatmomentsmvpdemo.moments.bean.CommentItem;
import com.wechatmomentsmvpdemo.moments.bean.FavortItem;
import com.wechatmomentsmvpdemo.moments.bean.Moment;
import com.wechatmomentsmvpdemo.moments.bean.PhotoInfo;
import com.wechatmomentsmvpdemo.moments.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kson on 2017/6/25.
 * 测试数据类
 */

public class DatasUtil {

    public static final String[] CONTENTS = {"",
            "哈哈， 6月24日，北京市中考正式开考，据北京市教育考试院此前公布的数据，2017年北京市将有7.2万考生参加中考，报考人数比去年有所减少。",
            //"今天是个好日子，http://www.ChinaAr.com;一个不错的VR网站,18123456789,",
            //"呵呵，http://www.ChinaAr.com;一个不错的VR网站,18123456789,",
            //"只有http|https|ftp|svn://开头的网址才能识别为网址，正则表达式写的不太好，如果你又更好的正则表达式请评论告诉我，谢谢！",
            "一场吸引了中国小半个投资圈关注的《王者荣耀》比赛结束了。Super Godlike 战队以 2 比 1 的总比分战胜猥琐发育别浪战队，获得冠军。\n" +
                    "\n" +
                    "冠军队中，一位无法赶到现场的队员在义乌高铁站打完了整场比赛。他原本的计划是从上海去福州，旅途中临时下了车，只为比赛中稳定的网络信号。\n" +
                    "\n" +
                    "当比赛结束，这位冠军成员再也买不到当天下一趟从义乌到福州的车票了。来自盛景嘉成母基金的队长刘迪豪气地说：你就找一个最豪华的酒店住，我给你付钱！\n" +
                    "\n" +
                    "决赛参赛双方的十名队员，分别来自十家不同的投行、基金、创业公司、以及上市公司投资部，每队成员所在机构加起来控制的资金都数以百亿计。\n" +
                    "\n" +
                    "这款国民级手游成为了中国投资圈最时髦的标签：面试的时候，面试官会邀请候选人来打一局《王者荣耀》，考验他的大局观、应变能力、抗压能力；投项目的时候，投资人会要求创业团队开黑打一局《王者荣耀》，以此观察他们的团队协作能力。\n" +
                    "\n" +
                    "尽管是个流传的段子，却证明了这个受过良好教育、掌握大量资本的投资阶层，对一款手游的集体投入。",
            "哈哈哈哈",
            "图不错",
            "真的吗"};
    /*public static final String[] PHOTOS = {
            "http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fecc3e83ef586034a85edf723d.jpg",
            "http://cdn.duitang.com/uploads/item/201309/17/20130917111400_CNmTr.thumb.224_0.png",
            "http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg",
            "http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg",
            "http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg",
            "http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg",
            "http://pic6.nipic.com/20100330/4592428_113348099353_2.jpg",
            "http://pic9.nipic.com/20100917/5653289_174356436608_2.jpg",
            "http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
            "http://pic1.nipic.com/2008-12-09/200812910493588_2.jpg",
            "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg" };*/
    public static final String[] HEADIMG = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498414856383&di=7df9d7f18a260446e4808c935a98ad4e&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-01-12%2F012800744.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4040007948,1328682906&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498414799271&di=4543fdcb088ee4fbea4e9d74bd2248b6&imgtype=0&src=http%3A%2F%2Fimg.weiyangx.com%2F2014%2F01%2F1231.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498414856383&di=7df9d7f18a260446e4808c935a98ad4e&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-01-12%2F012800744.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2482580784,3595026366&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498414701423&di=ea1c995a3dd0017ae4bfb243fad96f98&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1408%2F21%2F5311302_03_thumb.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2499486498,1578079066&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2231754418,153353052&fm=26&gp=0.jpg"};

    public static List<User> users = new ArrayList<User>();
    public static List<PhotoInfo> PHOTOS = new ArrayList<>();
    /**
     * 动态id自增长
     */
    private static int circleId = 0;
    /**
     * 点赞id自增长
     */
    private static int favortId = 0;
    /**
     * 评论id自增长
     */
    private static int commentId = 0;
    public static final User curUser = new User("0", "我", HEADIMG[0]);

    static {
        User user1 = new User("1", "马云", HEADIMG[1]);
        User user2 = new User("2", "马化腾", HEADIMG[2]);
        User user3 = new User("3", "kson", HEADIMG[3]);
        User user4 = new User("4", "罗永浩", HEADIMG[4]);
        User user5 = new User("5", "雷军", HEADIMG[5]);
        User user6 = new User("6", "刘强东", HEADIMG[6]);
        User user7 = new User("7", "奶茶妹妹是不是很长，哈哈！还行不是很长，换了吗，的确换了！", HEADIMG[7]);

        users.add(curUser);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);

        PhotoInfo p1 = new PhotoInfo();
        p1.url = "http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg";
        p1.w = 640;
        p1.h = 792;

        PhotoInfo p2 = new PhotoInfo();
        p2.url = "http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg";
        p2.w = 640;
        p2.h = 792;

        PhotoInfo p3 = new PhotoInfo();
        p3.url = "http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg";
        p3.w = 950;
        p3.h = 597;

        PhotoInfo p4 = new PhotoInfo();
        p4.url = "http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg";
        p4.w = 533;
        p4.h = 800;

        PhotoInfo p5 = new PhotoInfo();
        p5.url = "http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg";
        p5.w = 700;
        p5.h = 467;

        PhotoInfo p6 = new PhotoInfo();
        p6.url = "http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg";
        p6.w = 700;
        p6.h = 467;

        PhotoInfo p7 = new PhotoInfo();
        p7.url = "http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg";
        p7.w = 1024;
        p7.h = 640;

        PhotoInfo p8 = new PhotoInfo();
        p8.url = "http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg";
        p8.w = 1024;
        p8.h = 768;

        PhotoInfo p9 = new PhotoInfo();
        p9.url = "http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg";
        p9.w = 1024;
        p9.h = 640;

        PhotoInfo p10 = new PhotoInfo();
        p10.url = "http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg";
        p10.w = 1024;
        p10.h = 768;

        PHOTOS.add(p1);
        PHOTOS.add(p2);
        PHOTOS.add(p3);
        PHOTOS.add(p4);
        PHOTOS.add(p5);
        PHOTOS.add(p6);
        PHOTOS.add(p7);
        PHOTOS.add(p8);
        PHOTOS.add(p9);
        PHOTOS.add(p10);
    }

    public static List<Moment> createMomentDatas() {
        List<Moment> momentDatas = new ArrayList<Moment>();
        for (int i = 0; i < 15; i++) {
            Moment item = new Moment();
            User user = getUser();
            item.setId(String.valueOf(circleId++));
            item.setUser(user);
            item.setContent(getContent());
            item.setCreateTime("12月24日");

            item.setFavorters(createFavortItemList());
            item.setComments(createCommentItemList());
            int type = getRandomNum(10) % 2;
            if (type == 0) {
                item.setType("1");// 链接
                item.setLinkImg("http://pics.sc.chinaz.com/Files/pic/icons128/2264/%E8%85%BE%E8%AE%AFQQ%E5%9B%BE%E6%A0%87%E4%B8%8B%E8%BD%BD1.png");
                item.setLinkTitle("微信，微信不能全信");
            } else if (type == 1) {
                item.setType("2");// 图片
                item.setPhotos(createPhotos());
            } else {
                item.setType("3");// 视频
                String videoUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.mp4";
                String videoImgUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.jpg";
                item.setVideoUrl(videoUrl);
                item.setVideoImgUrl(videoImgUrl);
            }
            momentDatas.add(item);
        }

        return momentDatas;
    }

    public static User getUser() {
        return users.get(getRandomNum(users.size()));
    }

    public static String getContent() {
        return CONTENTS[getRandomNum(CONTENTS.length)];
    }

    public static int getRandomNum(int max) {
        Random random = new Random();
        int result = random.nextInt(max);
        return result;
    }

    public static List<PhotoInfo> createPhotos() {
        List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
        int size = getRandomNum(PHOTOS.size());
        if (size > 0) {
            if (size > 9) {
                size = 9;
            }
            for (int i = 0; i < size; i++) {
                PhotoInfo photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
                if (!photos.contains(photo)) {
                    photos.add(photo);
                } else {
                    i--;
                }
            }
        }
        return photos;
    }

    public static List<FavortItem> createFavortItemList() {
        int size = getRandomNum(users.size());
        List<FavortItem> items = new ArrayList<FavortItem>();
        List<String> history = new ArrayList<String>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                FavortItem newItem = createFavortItem();
                String userid = newItem.getUser().getId();
                if (!history.contains(userid)) {
                    items.add(newItem);
                    history.add(userid);
                } else {
                    i--;
                }
            }
        }
        return items;
    }

    public static FavortItem createFavortItem() {
        FavortItem item = new FavortItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(getUser());
        return item;
    }

    public static FavortItem createCurUserFavortItem() {
        FavortItem item = new FavortItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(curUser);
        return item;
    }

    public static List<CommentItem> createCommentItemList() {
        List<CommentItem> items = new ArrayList<CommentItem>();
        int size = getRandomNum(10);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                items.add(createComment());
            }
        }
        return items;
    }

    public static CommentItem createComment() {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent("吼吼");
        User user = getUser();
        item.setUser(user);
        if (getRandomNum(10) % 2 == 0) {
            while (true) {
                User replyUser = getUser();
                if (!user.getId().equals(replyUser.getId())) {
                    item.setToReplyUser(replyUser);
                    break;
                }
            }
        }
        return item;
    }

    /**
     * 创建发布评论
     *
     * @return
     */
    public static CommentItem createPublicComment(String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        return item;
    }

    /**
     * 创建回复评论
     *
     * @return
     */
    public static CommentItem createReplyComment(User replyUser, String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        item.setToReplyUser(replyUser);
        return item;
    }


    public static Moment createVideoItem(String videoUrl, String imgUrl) {
        Moment item = new Moment();
        item.setId(String.valueOf(circleId++));
        item.setUser(curUser);
        //item.setContent(getContent());
        item.setCreateTime("6月25日");

        //item.setFavorters(createFavortItemList());
        //item.setComments(createCommentItemList());
        item.setType("3");// 图片
        item.setVideoUrl(videoUrl);
        item.setVideoImgUrl(imgUrl);
        return item;
    }
}
