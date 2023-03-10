//
//  ItemScreen.swift
//  TestPractice
//
//  Created by taehoon lee on 2023/03/10.
//

import Example
import SwiftUI

struct ItemScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return ScreenProvider.shared.createItemViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
