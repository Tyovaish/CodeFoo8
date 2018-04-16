//
//  ContentDateManager.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 4/12/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import Foundation
class ContentDateManager {
    static func getDateFromRFC3339( dateString : String) -> Date{
        let RFC3339DateFormatter = DateFormatter()
        RFC3339DateFormatter.locale=Locale(identifier: "en_US_POSIX")
        RFC3339DateFormatter.dateFormat="yyyy-MM-dd'T'HH:mm:ssZZZZ"
        RFC3339DateFormatter.timeZone=TimeZone(secondsFromGMT: 0)
        return RFC3339DateFormatter.date(from: dateString)!
    }
    
    static func getDateDisplayString(content : Content) -> String {
        var currentDate = Calendar.current.dateComponents([.year,.month,.day,.hour,.minute,.second], from: Date())
        var contentPublishDateComponents=Calendar.current.dateComponents([.year,.month,.day,.hour,.minute,.second], from: content.contentPublishDate)
        if(currentDate.year! - contentPublishDateComponents.year!>0 || currentDate.month! - contentPublishDateComponents.month!>0 || currentDate.day! - contentPublishDateComponents.day!>1){
            let dateFormatter=DateFormatter()
            dateFormatter.locale=Locale(identifier: "en_US")
            dateFormatter.setLocalizedDateFormatFromTemplate("MMMMdyyyy")
            return dateFormatter.string(from: content.contentPublishDate)
        }
        if(currentDate.day!-contentPublishDateComponents.day!==1){
            return "YESTERDAY"
        }
        if(currentDate.hour!-contentPublishDateComponents.hour!>0){
            return "\(currentDate.hour!-contentPublishDateComponents.hour!) HR AGO"
        }
        if(currentDate.minute!-contentPublishDateComponents.minute!>0){
            return "\(currentDate.hour!-contentPublishDateComponents.hour!) MIN AGO"
        }
        if(currentDate.second!-contentPublishDateComponents.second!>0){
            return "\(currentDate.second!-contentPublishDateComponents.second!) SEC AGO"
        }
        return "NOW"
    }
}
