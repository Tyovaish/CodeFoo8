//
//  Content.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/22/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import Foundation
import UIKit
class Content{
    var contentType = String()
    var contentTitle = String()
    var contentId = String()
    var contentSlug = String()
    var urlLocation : URL?
    var amountOfComments = 0
    var quickDescription = String()
    var timePosted = String()
    var contentImageURL: URL?
    var contentImage : UIImage?
    var commentCount : String?
    var contentPublishDate : Date = Date()
    
}
