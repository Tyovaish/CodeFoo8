//
//  ContentURLExtractor.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 4/12/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import Foundation

class ContentURLExtractor {
    static func getContentWebURL(content : Content) -> URL {
        var contentPublishDateComponents=Calendar.current.dateComponents([.year,.month,.day], from: content.contentPublishDate)
        var urlString="https://m.ign.com/"+content.contentType+"s"
        urlString+="/"+String(describing: contentPublishDateComponents.year!)
        if(contentPublishDateComponents.month! < 10){
            urlString+="/0"+String(describing: contentPublishDateComponents.month!)
        } else {
            urlString+="/"+String(describing: contentPublishDateComponents.month!)
        }
        if(contentPublishDateComponents.day! < 10){
            urlString+="/0"+String(describing: contentPublishDateComponents.day!)
        } else {
            urlString+="/"+String(describing: contentPublishDateComponents.day!)
        }
        urlString+="/"+content.contentSlug
        return URL(string: urlString)!
    }
}
