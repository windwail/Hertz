package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import checkmobile.de.hertz.holder.IconTreeItemHolder;
import checkmobile.de.hertz.holder.ProfileHolder;
import checkmobile.de.hertz.holder.SelectableHeaderHolder;
import checkmobile.de.hertz.holder.SelectableItemHolder;

public class VehicleAccessoriesActivity extends AppCompatActivity {

    private AndroidTreeView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_accessories);

        ViewGroup containerView = (ViewGroup) findViewById(R.id.container);

        TreeNode root = TreeNode.root();

        TreeNode s1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_sd_storage, "Storage1")).setViewHolder(new ProfileHolder(this));
        TreeNode s2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_sd_storage, "Storage2")).setViewHolder(new ProfileHolder(this));
        s1.setSelectable(false);
        s2.setSelectable(false);

        TreeNode folder1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 1")).setViewHolder(new SelectableHeaderHolder(this));
        TreeNode folder2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 2")).setViewHolder(new SelectableHeaderHolder(this));
        TreeNode folder3 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 3")).setViewHolder(new SelectableHeaderHolder(this));

        fillFolder(folder1);
        fillFolder(folder2);
        fillFolder(folder3);

        s1.addChildren(folder1, folder2);
        s2.addChildren(folder3);

        root.addChildren(s1, s2);

        tView = new AndroidTreeView(this, root);
        tView.setDefaultAnimation(true);
        tView.setSelectionModeEnabled(true);
        containerView.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
    }

    private void fillFolder(TreeNode folder) {
        TreeNode file1 = new TreeNode("File1").setViewHolder(new SelectableItemHolder(this));
        TreeNode file2 = new TreeNode("File2").setViewHolder(new SelectableItemHolder(this));
        TreeNode file3 = new TreeNode("File3").setViewHolder(new SelectableItemHolder(this));
        folder.addChildren(file1, file2, file3);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
