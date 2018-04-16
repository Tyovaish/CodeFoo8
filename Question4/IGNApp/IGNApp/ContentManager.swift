//
//  ContentManager.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/22/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import Foundation
import SwiftyJSON
class ContentManager{
    static var contentManager = ContentManager();
    static var articleList = [Content]()
    static var videoList = [Content]()
    static var currentlyFetching = false
    static let contentGrabSize = 20
    
    static func getInstance()->ContentManager {
        return contentManager
    }
    
    static func getMoreContentData( contentTable : UITableView){
        if(currentlyFetching==true || articleList.count+videoList.count>=300){
            return
        }
        currentlyFetching=true
        let contentURL = URL(string: "https://ign-apis.herokuapp.com/content?startIndex=\(articleList.count+videoList.count)&count=\(contentGrabSize)")!
        var contentRequest = URLRequest(url:contentURL)
        contentRequest.httpMethod="GET"
        contentRequest.setValue("application/json",forHTTPHeaderField: "Accept")
        let contentTask = URLSession.shared.dataTask(with: contentRequest){
            data,response,error in
            let contentList = try? JSON(data: data!)
            var  grabbedContent = [Content]()
            for content in contentList!["data"].array! {
                let contentObject : Content = Content()
                contentObject.contentType=content["metadata"]["contentType"].stringValue
                contentObject.contentTitle=content["metadata"]["title"].stringValue
                contentObject.quickDescription=content["metadata"]["description"].stringValue
                contentObject.contentId=content["contentId"].stringValue
                contentObject.contentSlug=content["metadata"]["slug"].stringValue
                contentObject.contentImageURL=URL(string: content["thumbnails"][1]["url"].stringValue)!
                contentObject.contentPublishDate=ContentDateManager.getDateFromRFC3339(dateString:content["metadata"]["publishDate"].stringValue)
                sortContent(content: contentObject)
                grabbedContent.append(contentObject)
            }
            getCommentCount(contentList: grabbedContent, contentTable: contentTable)
            DispatchQueue.main.async{
                 contentTable.reloadData()
            }
            self.currentlyFetching=false
        }
        contentTask.resume()
        
    }
    static func getCommentCount(contentList : [Content], contentTable : UITableView){
        
        var commentURLString = String("https://ign-apis.herokuapp.com/comments?ids=")
        for i in 0..<contentList.count{
            commentURLString+=contentList[i].contentId
            if(i != contentList.count-1){
                commentURLString+=","
            }
        }
        let commentURL = URL(string: commentURLString)!
        var commentRequest = URLRequest(url:commentURL)
        commentRequest.httpMethod="GET"
        commentRequest.setValue("application/json",forHTTPHeaderField: "Accept")
        let commentTask = URLSession.shared.dataTask(with: commentRequest){
            data,response,error in
            let commentList = try? JSON(data: data!)
            for comment in commentList!["content"].array! {
                for content in contentList {
                    if(content.contentId==comment["id"].stringValue){
                        content.commentCount=comment["count"].stringValue
                        break
                    }
                }
            }
            DispatchQueue.main.async{
                contentTable.reloadData()
            }
        }
        commentTask.resume()
    }
    
    static func sortContent(content: Content){
        if content.contentType == "article" {
            self.articleList.append(content)
        } else if content.contentType=="video" {
            self.videoList.append(content)
        }
    }
    static func getVideoContentSize() -> Int{
        return videoList.count
    }
    static func getArticleContentSize()->Int{
        return articleList.count
    }
    static func getArticle( index : Int)->Content{
        return articleList[index]
    }
    static func getVideo(index : Int)->Content{
        return videoList[index]
    }
}

