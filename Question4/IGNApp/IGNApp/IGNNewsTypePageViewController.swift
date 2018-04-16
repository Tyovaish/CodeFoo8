//
//  IGNNewsTypePageViewController.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/21/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import UIKit
import XLPagerTabStrip

class IGNNewsTypePageViewController: ButtonBarPagerTabStripViewController {
    override func viewDidLoad() {
        self.settings.style.selectedBarHeight=2
        self.settings.style.selectedBarBackgroundColor=UIColor.red
        self.settings.style.buttonBarBackgroundColor=UIColor.white
        self.settings.style.buttonBarItemBackgroundColor=UIColor.white
        self.settings.style.buttonBarItemTitleColor=UIColor.red
        self.settings.style.buttonBarItemFont=UIFont(name: "Helvetica", size: 17)!
        changeCurrentIndexProgressive = { (oldCell: ButtonBarViewCell?, newCell: ButtonBarViewCell?, progressPercentage: CGFloat, changeCurrentIndex: Bool, animated: Bool) -> Void in
            guard changeCurrentIndex == true else { return }
            oldCell?.label.textColor = UIColor.gray
            newCell?.label.textColor = UIColor.red
        }
        super.viewDidLoad()

        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override public func viewControllers(for pagerTabStripController: PagerTabStripViewController) -> [UIViewController] {
        let articleController = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "Article") as! ArticleViewController
        let videoController = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "Video") as! VideoViewController
        return [articleController, videoController]
    }
    
    
}
