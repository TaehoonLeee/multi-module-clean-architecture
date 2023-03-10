//
//  GalleryScreen.swift
//  TestPractice
//
//  Created by taehoon lee on 2023/03/10.
//

import Example
import SwiftUI

struct GalleryScreen: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> some UIViewController {
        return ScreenProvider.shared.createGalleryViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
