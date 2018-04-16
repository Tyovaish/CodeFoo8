//
//  NewsContentCell.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/22/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import UIKit

class NewsContentCell: UITableViewCell{
    @IBOutlet var timePosted: UILabel!
    @IBOutlet var contentImage: UIImageView!
    @IBOutlet var quickDescription: UILabel!
    @IBOutlet var contentAction: UILabel!
    @IBOutlet var commentCount: UILabel!
    @IBOutlet var contentTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
   
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    

}
