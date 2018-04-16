//
//  ArticleViewController.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/21/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//
import SDWebImage
import UIKit
import XLPagerTabStrip

class ArticleViewController: UIViewController, IndicatorInfoProvider {
    @IBOutlet var articleTable: UITableView!
    var refresher : UIRefreshControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        articleTable.dataSource=self
        articleTable.delegate=self
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(refreshData), for: UIControlEvents.valueChanged)
        articleTable.addSubview(refresher)
        if(ContentManager.getArticleContentSize()==0){
            ContentManager.getMoreContentData(contentTable: articleTable)
        }
    }
    override func viewDidAppear(_ animated: Bool) {
        articleTable.reloadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func indicatorInfo(for pagerTabStripController: PagerTabStripViewController) -> IndicatorInfo {
        return IndicatorInfo(title: "ARTICLES")
    }
    
    @objc func refreshData(){
        refresher.beginRefreshing()
        refresher.endRefreshing()
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
 

}; extension ArticleViewController : UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return ContentManager.getArticleContentSize()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "NewsContentCell", for: indexPath) as! NewsContentCell
        let content = ContentManager.getArticle(index:indexPath.row)
        cell.contentTitle.text = content.contentTitle
        cell.quickDescription.text=content.quickDescription
        cell.contentImage.sd_setImage(with: content.contentImageURL!)
        cell.contentAction.text="Read"
        cell.timePosted.text=ContentDateManager.getDateDisplayString(content: content)
        cell.commentCount.text = content.commentCount
        
        return cell
    }
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath){
        if(indexPath.row == ContentManager.getArticleContentSize()-1){
            ContentManager.getMoreContentData(contentTable: articleTable)
        }
    }
}; extension ArticleViewController : UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath){
       self.parent!.parent!.performSegue(withIdentifier: "webView", sender: ContentManager.articleList[indexPath.row])
    }
};
